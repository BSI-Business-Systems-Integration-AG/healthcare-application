package org.eclipse.scout.healthcare.client;

import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.shared.TEXTS;

public class AbstractStartSimulationMenu extends AbstractMenu {
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
    super.execAction();
  }

}
