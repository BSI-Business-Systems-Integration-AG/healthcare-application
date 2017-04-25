package org.eclipse.scout.healthcare.shared.devices;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IDeviceService extends IService {

  /**
   * @param filter
   * @return
   */
  DeviceTablePageData getDevicesTableData(SearchFilter filter);
}
