package org.eclipse.scout.healthcare.client.person;

import java.util.List;

import org.eclipse.scout.healthcare.client.ethereum.AccountTablePage;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;

public class PersonNodePage extends AbstractPageWithNodes {

  private String personId;

  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    AccountTablePage accountTablePage = new AccountTablePage();
    accountTablePage.setPersonId(getPersonId());
    pageList.add(accountTablePage);
  }
}
