package org.eclipse.scout.healthcare.client.disinfection.simulation;

import org.eclipse.scout.healthcare.client.ClientSession;
import org.eclipse.scout.healthcare.client.Icons;
import org.eclipse.scout.healthcare.client.StandardOutline;
import org.eclipse.scout.healthcare.client.device.DeviceTablePage;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.HandDisinfectionEventSimulationFormData;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.IHandDisinfectionEventSimulationService;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.notification.DesktopNotification;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.status.IStatus;
import org.eclipse.scout.rt.platform.status.Status;
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
    HandDisinfectionEventSimulationForm form = new HandDisinfectionEventSimulationForm();
    form.startNew();
    form.waitFor();
    if (form.isFormClosed()) {
      HandDisinfectionEventSimulationFormData formData = new HandDisinfectionEventSimulationFormData();
      form.exportFormData(formData);
      startSimulation(formData);
      showSimulationStartedNotification(formData.getDeviceDisplayText().getValue(), formData.getEmployeeDisplayText().getValue());
      activateDeviceTableView();
    }
  }

  private void startSimulation(HandDisinfectionEventSimulationFormData formData) {
    BEANS.get(IHandDisinfectionEventSimulationService.class).simulate(formData);
  }

  @SuppressWarnings("null")
  private void activateDeviceTableView() {
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
      if (!standardOutline.isSelectedNode(deviceTablePage)) {
        standardOutline.selectNode(deviceTablePage);
        deviceTablePage.reloadPage();
        desktop.activateOutline(standardOutline);
      }
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
