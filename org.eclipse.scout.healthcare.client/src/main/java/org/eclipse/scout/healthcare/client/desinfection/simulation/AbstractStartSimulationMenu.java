package org.eclipse.scout.healthcare.client.desinfection.simulation;

import org.eclipse.scout.healthcare.client.ClientSession;
import org.eclipse.scout.healthcare.client.Icons;
import org.eclipse.scout.healthcare.client.StandardOutline;
import org.eclipse.scout.healthcare.client.device.DeviceTablePage;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

public class AbstractStartSimulationMenu extends AbstractMenu {
//  private static final Logger LOG = LoggerFactory.getLogger(AbstractStartSimulationMenu.class);

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("SimulateDesinfection");
  }

  @Override
  protected String getConfiguredKeyStroke() {
    return IKeyStroke.F10;
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Desinfacting;
  }

  @Override
  protected void execAction() {
    HandDesinfectionEventSimulationForm form = new HandDesinfectionEventSimulationForm();
    form.startNew();
    form.waitFor();
    if (form.isFormStored()) {
      IDesktop desktop = ClientSession.get().getDesktop();
      if (null != desktop) {
        IOutline standardOutline = null;
        for (IOutline outline : desktop.getAvailableOutlines()) {
          if (outline instanceof StandardOutline) {
            standardOutline = outline;
            break;
          }
        }
        if (null != standardOutline) {
          IPage deviceTablePage = standardOutline.findPage(DeviceTablePage.class);
          if (deviceTablePage != null) {
            if (!standardOutline.isSelectedNode(deviceTablePage)) {
              standardOutline.selectNode(deviceTablePage);
              deviceTablePage.reloadPage();
              desktop.activateOutline(standardOutline);
            }
          }
        }
      }
    }
  }

}
