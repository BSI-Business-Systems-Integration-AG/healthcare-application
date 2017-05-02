package org.eclipse.scout.healthcare.shared.disinfection;

import org.eclipse.scout.healthcare.shared.disinfection.DisinfectionTablePageData;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

/**
 * <h3>{@link IDisinfectionService}</h3>
 *
 * @author uko
 */
@TunnelToServer
public interface IDisinfectionService extends IService {

  /**
   * @param filter
   * @return
   */
  DisinfectionTablePageData getDisinfectionTableData(SearchFilter filter);
}
