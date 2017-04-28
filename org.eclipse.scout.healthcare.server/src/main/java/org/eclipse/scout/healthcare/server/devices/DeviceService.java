package org.eclipse.scout.healthcare.server.devices;

import org.eclipse.scout.healthcare.server.sql.DeviceSQLs;
import org.eclipse.scout.healthcare.shared.device.DeviceOverviewFormData;
import org.eclipse.scout.healthcare.shared.devices.DeviceTablePageData;
import org.eclipse.scout.healthcare.shared.devices.IDeviceService;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class DeviceService implements IDeviceService {

  @Override
  public DeviceTablePageData getDevicesTableData(SearchFilter filter) {
    DeviceTablePageData pageData = new DeviceTablePageData();

    StringBuilder sql = new StringBuilder();

    sql.append(DeviceSQLs.PAGE_SELECT);
    sql.append(" WHERE 1 = 1 ");

    sql.append(DeviceSQLs.PAGE_DATA_SELECT_INTO);

    SQL.selectInto(sql.toString(), new NVPair("page", pageData));

    return pageData;
  }

  @Override
  public DeviceOverviewFormData load(String deviceId) {
    DeviceOverviewFormData formData = new DeviceOverviewFormData();
    formData.getDeviceId().setValue(deviceId);
    SQL.selectInto(DeviceSQLs.LOAD, formData);

    return formData;
  }

}
