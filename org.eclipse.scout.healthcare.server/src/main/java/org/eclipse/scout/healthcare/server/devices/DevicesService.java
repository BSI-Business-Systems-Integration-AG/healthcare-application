package org.eclipse.scout.healthcare.server.devices;

import org.eclipse.scout.healthcare.server.sql.DeviceSQLs;
import org.eclipse.scout.healthcare.shared.devices.DevicesTablePageData;
import org.eclipse.scout.healthcare.shared.devices.IDevicesService;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class DevicesService implements IDevicesService {

  @Override
  public DevicesTablePageData getDevicesTableData(SearchFilter filter) {
    DevicesTablePageData pageData = new DevicesTablePageData();

    StringBuilder sql = new StringBuilder();

    sql.append(DeviceSQLs.PAGE_SELECT);
    sql.append(" WHERE 1 = 1 ");

    sql.append(DeviceSQLs.PAGE_DATA_SELECT_INTO);

    SQL.selectInto(sql.toString(), new NVPair("page", pageData));

    return pageData;
  }
}
