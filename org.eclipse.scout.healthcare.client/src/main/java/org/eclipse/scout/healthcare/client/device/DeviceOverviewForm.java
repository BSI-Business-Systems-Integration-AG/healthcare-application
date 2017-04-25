package org.eclipse.scout.healthcare.client.device;

import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.DeviceNrField;
import org.eclipse.scout.healthcare.shared.device.DeviceOverviewFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;

@FormData(value = DeviceOverviewFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class DeviceOverviewForm extends AbstractForm {

  private String m_deviceNr;

  public DeviceOverviewForm(String deviceNr) {
    m_deviceNr = deviceNr;
  }

  @Override
  protected String getConfiguredTitle() {
    String title = TEXTS.get("Devices");
    if (StringUtility.hasText(m_deviceNr)) {
      title = m_deviceNr;
    }
    return title;
  }

  public void startDefault() {
    startInternal(new DefaultHandler());
  }

  public DetailsBox getDetailsBox() {
    return getFieldByClass(DetailsBox.class);
  }

  public DeviceNrField getDeviceNrField() {
    return getFieldByClass(DeviceNrField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(1000)
  public class MainBox extends AbstractGroupBox {

    @Order(1000)
    public class DetailsBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Details");
      }

      @Order(1000)
      public class DeviceNrField extends AbstractLabelField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Device-Nr");
        }
      }

    }

  }

  public class DefaultHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() {
    }

    @Override
    protected void execStore() {
    }
  }
}
