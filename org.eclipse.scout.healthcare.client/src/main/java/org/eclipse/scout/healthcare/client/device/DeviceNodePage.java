package org.eclipse.scout.healthcare.client.device;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;

public class DeviceNodePage extends AbstractPageWithNodes {

  private String m_deviceNr;

  public DeviceNodePage(String deviceNr) {
    m_deviceNr = deviceNr;
  }

  @Override
  protected String getConfiguredTitle() {
    String title = TEXTS.get("DeviceOverview");
    if (StringUtility.hasText(m_deviceNr)) {
      title = m_deviceNr;
    }
    return title;
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    // TODO [uko] Auto-generated method stub.
    super.execCreateChildPages(pageList);
  }

//  @Override
//  protected Class<? extends IForm> getConfiguredDetailForm() {
//    return DeviceOverviewForm.class;
//  }

  @Override
  protected void execPageActivated() {
    super.execPageActivated();

    if (null == getDetailForm()) {
      DeviceOverviewForm overviewForm = new DeviceOverviewForm(m_deviceNr);
      setDetailForm(overviewForm);
      overviewForm.startDefault();
    }
  }
}
