package org.eclipse.scout.healthcare.shared.devices;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

/**
 * <h3>{@link IDevicesService}</h3>
 *
 * @author uk
 */
@TunnelToServer
public interface IDevicesService extends IService {

  /**
   * @param filter
   * @return
   */
  DevicesTablePageData getDevicesTableData(SearchFilter filter);
}
