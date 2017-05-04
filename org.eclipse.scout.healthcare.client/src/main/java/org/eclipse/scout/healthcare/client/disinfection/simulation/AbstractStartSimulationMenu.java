package org.eclipse.scout.healthcare.client.disinfection.simulation;

import java.util.List;

import org.eclipse.scout.healthcare.client.ClientSession;
import org.eclipse.scout.healthcare.client.Icons;
import org.eclipse.scout.healthcare.client.StandardOutline;
import org.eclipse.scout.healthcare.client.device.DeviceNodePage;
import org.eclipse.scout.healthcare.client.device.DeviceTablePage;
import org.eclipse.scout.healthcare.client.disinfection.simulation.HandDisinfectionSimulationProperties.HandDisinfectionSimulationShowFormProperty;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.HandDisinfectionEventSimulationFormData;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.IHandDisinfectionEventSimulationService;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.basic.tree.ITreeNode;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.notification.DesktopNotification;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.status.IStatus;
import org.eclipse.scout.rt.platform.status.Status;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractStartSimulationMenu extends AbstractMenu {
  private static final Logger LOG = LoggerFactory.getLogger(AbstractStartSimulationMenu.class);

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("SimulateDisinfection");
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
    HandDisinfectionEventSimulationFormData formData = null;
    if (CONFIG.getPropertyValue(HandDisinfectionSimulationShowFormProperty.class)) {
      HandDisinfectionEventSimulationForm form = new HandDisinfectionEventSimulationForm();
      form.startNew();
      form.waitFor();
      if (form.isFormClosed() && form.isFormStored()) {
        formData = new HandDisinfectionEventSimulationFormData();
        form.exportFormData(formData);
      }
    }
    else {
      formData = new HandDisinfectionEventSimulationFormData();
      formData = BEANS.get(IHandDisinfectionEventSimulationService.class).prepareSimulate(formData, false);
    }

    if (null != formData) {
      startSimulation(formData);
      showSimulationStartedNotification(formData.getDeviceDisplayText().getValue(), formData.getEmployeeDisplayText().getValue());
      activateDeviceTableView(formData.getDevice().getValue());
    }
  }

  private void startSimulation(HandDisinfectionEventSimulationFormData formData) {
    BEANS.get(IHandDisinfectionEventSimulationService.class).simulate(formData);
  }

  @SuppressWarnings("null")
  private void activateDeviceTableView(String deviceId) {
    IDesktop desktop = ClientSession.get().getDesktop();
    try {
      IOutline standardOutline = null;
      for (IOutline outline : desktop.getAvailableOutlines()) {
        if (outline instanceof StandardOutline) {
          standardOutline = outline;
          break;
        }
      }
      IPage deviceTablePage = standardOutline.findPage(DeviceTablePage.class);
      ITreeNode nodeToShow = deviceTablePage;
      deviceTablePage.loadChildren();
      List<?> pages = deviceTablePage.getChildPages();
      for (Object page : pages) {
        if (page instanceof DeviceNodePage) {
          DeviceNodePage deviceNode = (DeviceNodePage) page;
          if (StringUtility.equalsIgnoreCase(deviceNode.getDeviceId(), deviceId)) {
            nodeToShow = deviceNode;
            break;
          }
        }
      }

      if (!standardOutline.isSelectedNode(nodeToShow)) {
        standardOutline.selectNode(nodeToShow);
        if (nodeToShow instanceof IPage) {
          ((IPage) nodeToShow).reloadPage();
        }
      }
      desktop.activateOutline(standardOutline);
    }
    catch (Exception e) {
      LOG.error("Could not activate device table page", e);
    }
  }

  private void showSimulationStartedNotification(String device, String employee) {
    IDesktop desktop = ClientSession.get().getDesktop();
    try {
      IStatus status = new Status(TEXTS.get("SimulationStartedInfo", device, employee), IStatus.INFO);
      DesktopNotification notification = new DesktopNotification(status, 5000L, true);
      desktop.addNotification(notification);
    }
    catch (Exception e) {
      LOG.error("Could not show start simulation notification", e);
    }
  }
}
