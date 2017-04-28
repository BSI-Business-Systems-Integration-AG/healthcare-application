package org.eclipse.scout.healthcare.server.desinfection;

import org.eclipse.scout.healthcare.shared.desinfection.DesinfectionTablePageData;
import org.eclipse.scout.healthcare.shared.desinfection.IDesinfectionService;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class DesinfectionService implements IDesinfectionService {

  @Override
  public DesinfectionTablePageData getDesinfectionTableData(SearchFilter filter) {
    DesinfectionTablePageData pageData = new DesinfectionTablePageData();
    // TODO [uko] fill pageData.
    return pageData;
  }
}
