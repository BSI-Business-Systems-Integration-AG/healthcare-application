package org.eclipse.scout.healthcare.server.disinfection.simulation;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.eclipse.scout.healthcare.server.disinfection.model.HandDisinfectionEvent;
import org.eclipse.scout.healthcare.server.sql.DeviceSQLs;
import org.eclipse.scout.healthcare.server.sql.PersonSQLs;
import org.eclipse.scout.healthcare.server.sql.SuperUserRunContextProducer;
import org.eclipse.scout.healthcare.shared.device.DeviceOverviewFormData;
import org.eclipse.scout.healthcare.shared.devices.DeviceStatusCodeType;
import org.eclipse.scout.healthcare.shared.devices.IDeviceService;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.HandDisinfectionEventSimulationFormData;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.IHandDisinfectionEventSimulationService;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.job.Jobs;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class HandDisinfectionEventSimulationService implements IHandDisinfectionEventSimulationService {

  @Override
  public HandDisinfectionEventSimulationFormData prepareSimulate(HandDisinfectionEventSimulationFormData formData, boolean random) {
    if (!random) {
      formData.getDevice().setValue(DeviceSQLs.SIMULATION_DEVICE_ID);
      SQL.selectInto(DeviceSQLs.SELECT_DISPLAY_NAME, formData);
    }
    else {
      SQL.selectIntoLimited(DeviceSQLs.SELECT_RANDOM, 1, formData);
    }

    SQL.selectIntoLimited(PersonSQLs.SELECT_RANDOM, 1, formData);
    if (!random) {
      SQL.selectInto(PersonSQLs.SELECT_DISPLAY_TEXT, formData);
    }

    return formData;
  }

  @Override
  public HandDisinfectionEventSimulationFormData simulate(HandDisinfectionEventSimulationFormData formData) {
    simulateDisinfectionStart(formData);

    Long duration = getRandomDuration() + 2000L;
    Date evtStart = new Date();
    String jobName = "DisinfectionSimulation." + evtStart.getTime();
    Jobs.schedule(new IRunnable() {

      @Override
      public void run() throws Exception {
        // Simulate disinfection duration
        TimeUnit.MILLISECONDS.sleep(duration);
        simulateDisinfectionEnd(formData.getDevice().getValue(), formData.getEmployee().getValue(), evtStart, duration);
      }
    }, Jobs.newInput()
        .withName(jobName)
        .withRunContext(BEANS.get(SuperUserRunContextProducer.class).produce()));
    return formData;
  }

  private void simulateDisinfectionStart(HandDisinfectionEventSimulationFormData formData) {
    BEANS.get(IDeviceService.class).updateDeviceStatus(formData.getDevice().getValue(), DeviceStatusCodeType.DisinfectsCode.ID);
  }

  private void simulateDisinfectionEnd(String deviceId, String employeeId, Date evtStart, Long duration) {
    DeviceOverviewFormData deviceData = BEANS.get(IDeviceService.class).load(deviceId);
    String chemistry = deviceData.getChemistry().getValue();
    HandDisinfectionEvent event = new HandDisinfectionEvent(deviceId, employeeId, chemistry, evtStart, duration);
    BEANS.get(DisinfectionEventPollingSimulationService.class).addEvent(event);
    BEANS.get(IDeviceService.class).updateDeviceStatus(deviceId, DeviceStatusCodeType.ReadyCode.ID);
  }

  private Long getRandomDuration() {
    Long random = Math.abs(NumberUtility.randomLong());
    Long number = 7000L + ((long) (random % (9000L - 7000L)));
    return number;
  }

}
