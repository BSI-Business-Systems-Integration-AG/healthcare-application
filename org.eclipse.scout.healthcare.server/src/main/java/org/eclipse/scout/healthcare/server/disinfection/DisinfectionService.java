package org.eclipse.scout.healthcare.server.disinfection;

import org.eclipse.scout.healthcare.server.disinfection.model.HandDisinfectionEvent;
import org.eclipse.scout.healthcare.shared.disinfection.DisinfectionTablePageData;
import org.eclipse.scout.healthcare.shared.disinfection.DisinfectionTablePageData.DisinfectionTableRowData;
import org.eclipse.scout.healthcare.shared.disinfection.IDisinfectionService;
import org.eclipse.scout.rt.platform.BEANS;
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
}
