package org.eclipse.scout.healthcare.client.device;

import java.util.List;

import org.eclipse.scout.healthcare.client.disinfection.DisinfectionTablePage;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;

public class DeviceNodePage extends AbstractPageWithNodes {

  private String m_deviceId;

  public DeviceNodePage(String deviceId) {
    m_deviceId = deviceId;
  }

  public String getDeviceId() {
    return m_deviceId;
  }

  @Override
  protected String getConfiguredTitle() {
    String title = TEXTS.get("DeviceOverview");
    if (StringUtility.hasText(m_deviceId)) {
      title = m_deviceId;
    }
    return title;
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    DisinfectionTablePage disinfectionPage = new DisinfectionTablePage();
    disinfectionPage.setDeviceId(m_deviceId);
    pageList.add(disinfectionPage);
  }

  @Override
  protected void execInitPage() {
    super.execInitPage();

    if (null == getDetailForm()) {
      DeviceOverviewForm overviewForm = new DeviceOverviewForm(m_deviceId);
      setDetailForm(overviewForm);
      overviewForm.startDefault();
    }
  }
}
