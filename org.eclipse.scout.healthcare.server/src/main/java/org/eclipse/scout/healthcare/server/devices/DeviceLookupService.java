package org.eclipse.scout.healthcare.server.devices;

import org.eclipse.scout.healthcare.server.sql.DeviceSQLs;
import org.eclipse.scout.healthcare.shared.devices.IDeviceLookupService;
import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;

public class DeviceLookupService extends AbstractSqlLookupService<String> implements IDeviceLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    return DeviceSQLs.LOOKUP;
  }

}
