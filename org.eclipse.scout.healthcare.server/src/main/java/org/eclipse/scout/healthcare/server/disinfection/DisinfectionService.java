package org.eclipse.scout.healthcare.server.disinfection;

import java.util.Date;

import org.eclipse.scout.healthcare.server.disinfection.model.HandDisinfectionEvent;
import org.eclipse.scout.healthcare.server.disinfection.tracker.HandDisinfectionEventTrackerService;
import org.eclipse.scout.healthcare.server.sql.DeviceSQLs;
import org.eclipse.scout.healthcare.shared.devices.DeviceStatusCodeType;
import org.eclipse.scout.healthcare.shared.disinfection.DisinfectionTablePageData;
import org.eclipse.scout.healthcare.shared.disinfection.DisinfectionTablePageData.DisinfectionTableRowData;
import org.eclipse.scout.healthcare.shared.disinfection.IDisinfectionService;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class DisinfectionService implements IDisinfectionService {

  @Override
  public DisinfectionTablePageData getDisinfectionTableData(SearchFilter filter) {
    DisinfectionTablePageData pageData = new DisinfectionTablePageData();
    HandDisinfectionEvent[] events = BEANS.get(HandDisinfectionEventTrackerService.class).getAllHandDisinfectionEvents();
    for (HandDisinfectionEvent e : events) {
      DisinfectionTableRowData row = pageData.addRow();

    }
    // TODO [uko] fill pageData.
    return pageData;
  }

  @Override
  public void startDisinfection(String deviceId) {
    SQL.update(DeviceSQLs.UPDATE_STATUS,
        new NVPair("status", DeviceStatusCodeType.DesinfectsCode.ID),
        new NVPair("deviceId", deviceId));
  }

  @Override
  public void endDisinfection(String deviceId, String employeeId, Long duration) {
//    TODO [uko] load chemistry
//    DeviceOverviewFormData deviceData = BEANS.get(IDeviceService.class).load(deviceId);
//    String chemistry = deviceData.getChemistry().getValue();
    Date timestamp = new Date();
    HandDisinfectionEvent newEvent = new HandDisinfectionEvent(deviceId, employeeId, "Chem 124", timestamp, duration);

    newEvent = BEANS.get(HandDisinfectionEventTrackerService.class).trackHandDisinfectionEvent(newEvent);
    SQL.update(DeviceSQLs.UPDATE_STATUS,
        new NVPair("status", DeviceStatusCodeType.ReadyCode.ID),
        new NVPair("deviceId", deviceId));
  }
}
