package org.eclipse.scout.healthcare.client.person;

import java.util.List;

import org.eclipse.scout.healthcare.shared.person.OccupationCodeType;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

public class StandardPersonNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Employees");
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    pageList.add(new EmployeePersonTablePage(OccupationCodeType.DoctorCode.ID));
    pageList.add(new EmployeePersonTablePage(OccupationCodeType.NurseCode.ID));
  }
}
