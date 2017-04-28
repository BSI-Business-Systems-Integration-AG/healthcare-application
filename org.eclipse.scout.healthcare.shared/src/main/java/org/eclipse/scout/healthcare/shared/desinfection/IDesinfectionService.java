package org.eclipse.scout.healthcare.shared.desinfection;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

/**
 * <h3>{@link IDesinfectionService}</h3>
 *
 * @author uko
 */
@TunnelToServer
public interface IDesinfectionService extends IService {

  /**
   * @param filter
   * @return
   */
  DesinfectionTablePageData getDesinfectionTableData(SearchFilter filter);
}
