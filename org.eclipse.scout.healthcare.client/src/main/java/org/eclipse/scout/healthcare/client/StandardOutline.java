package org.eclipse.scout.healthcare.client;

import java.util.List;

import org.eclipse.scout.healthcare.client.device.DeviceTablePage;
import org.eclipse.scout.healthcare.client.person.StandardPersonNodePage;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

public class StandardOutline extends AbstractOutline {

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    pageList.add(new DeviceTablePage());
    pageList.add(new StandardPersonNodePage());
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Healthcare");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Heartbeat;
  }

}
