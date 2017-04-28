package org.eclipse.scout.healthcare.client.ethereum;

import java.util.Set;

import org.eclipse.scout.healthcare.client.ClientSession;
import org.eclipse.scout.healthcare.client.Icons;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.OpenUriAction;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;

public abstract class AbstractTrackOnlineMenu<T extends AbstractStringColumn> extends AbstractMenu {

  private T m_trackingUrlColumn;

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("TrackOnline");
  }

  @Override
  protected Set<? extends IMenuType> getConfiguredMenuTypes() {
    return CollectionUtility.hashSet(TableMenuType.SingleSelection);
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.OpenExternUri;
  }

  /**
   * This method is used internally on the initialization of the menu.
   *
   * @return Column which contains an URL to an external site, where you can track transactions in ethereum
   */
  protected abstract T getConfiguredTrackingUrlColumn();

  @Override
  protected void execInitAction() {
    m_trackingUrlColumn = getConfiguredTrackingUrlColumn();
  }

  @Override
  protected void execOwnerValueChanged(Object newOwnerValue) {
    if (null != m_trackingUrlColumn) {
      String trackingUrl = m_trackingUrlColumn.getSelectedValue();
      setEnabled(StringUtility.hasText(trackingUrl));
    }
    else {
      setEnabled(false);
    }
  }

  @Override
  protected void execAction() {
    if (null != m_trackingUrlColumn) {
      String trackingUrl = m_trackingUrlColumn.getSelectedValue();
      ClientSession.get().getDesktop().openUri(trackingUrl, OpenUriAction.NEW_WINDOW);
    }
  }

}
