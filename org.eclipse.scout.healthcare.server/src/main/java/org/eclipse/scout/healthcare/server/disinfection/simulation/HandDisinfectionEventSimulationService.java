package org.eclipse.scout.healthcare.server.disinfection.simulation;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.eclipse.scout.healthcare.server.sql.DeviceSQLs;
import org.eclipse.scout.healthcare.server.sql.PersonSQLs;
import org.eclipse.scout.healthcare.server.sql.SuperUserRunContextProducer;
import org.eclipse.scout.healthcare.shared.disinfection.IDisinfectionService;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.HandDisinfectionEventSimulationFormData;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.IHandDisinfectionEventSimulationService;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.job.Jobs;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class HandDisinfectionEventSimulationService implements IHandDisinfectionEventSimulationService {

  @Override
  public HandDisinfectionEventSimulationFormData prepareSimulate(HandDisinfectionEventSimulationFormData formData) {
    SQL.selectIntoLimited(DeviceSQLs.SELECT_RANDOM, 1, formData);
    SQL.selectIntoLimited(PersonSQLs.SELECT_RANDOM, 1, formData);
    return formData;
  }

  @Override
  public HandDisinfectionEventSimulationFormData simulate(HandDisinfectionEventSimulationFormData formData) {
    // TODO [uk] add business logic here.
    simulateDisinfectionStart(formData);

    Long duration = getRandomDuration();
    Date current = new Date();
    String jobName = "DisinfectionSimulation." + current.getTime();
    Jobs.schedule(new IRunnable() {

      @Override
      public void run() throws Exception {
        // Simulate disinfection duration
        TimeUnit.MILLISECONDS.sleep(duration);
        simulateDisinfectionEnd(formData, duration);
      }
    }, Jobs.newInput()
        .withName(jobName)
        .withRunContext(BEANS.get(SuperUserRunContextProducer.class).produce()));
    return formData;
  }

  private void simulateDisinfectionStart(HandDisinfectionEventSimulationFormData formData) {
    BEANS.get(IDisinfectionService.class).startDisinfection(formData.getDevice().getValue());
  }

  private void simulateDisinfectionEnd(HandDisinfectionEventSimulationFormData formData, Long duration) {
    BEANS.get(IDisinfectionService.class).endDisinfection(
        formData.getDevice().getValue(), formData.getEmployee().getValue(), duration);
  }

  private Long getRandomDuration() {
    Long random = Math.abs(NumberUtility.randomLong());
    Long number = 7000L + ((long) (random % (13000L - 7000L)));
    return number;
  }

}
