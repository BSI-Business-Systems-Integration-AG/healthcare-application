package org.eclipse.scout.healthcare.client.disinfection.simulation;

import org.eclipse.scout.healthcare.client.disinfection.simulation.HandDisinfectionEventSimulationForm.MainBox.CancelButton;
import org.eclipse.scout.healthcare.client.disinfection.simulation.HandDisinfectionEventSimulationForm.MainBox.EventInfoBox;
import org.eclipse.scout.healthcare.client.disinfection.simulation.HandDisinfectionEventSimulationForm.MainBox.OkButton;
import org.eclipse.scout.healthcare.client.disinfection.simulation.HandDisinfectionEventSimulationForm.MainBox.EventInfoBox.DeviceDisplayTextField;
import org.eclipse.scout.healthcare.client.disinfection.simulation.HandDisinfectionEventSimulationForm.MainBox.EventInfoBox.DeviceField;
import org.eclipse.scout.healthcare.client.disinfection.simulation.HandDisinfectionEventSimulationForm.MainBox.EventInfoBox.EmployeeDisplayTextField;
import org.eclipse.scout.healthcare.client.disinfection.simulation.HandDisinfectionEventSimulationForm.MainBox.EventInfoBox.EmployeeField;
import org.eclipse.scout.healthcare.shared.devices.DeviceLookupCall;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.HandDisinfectionEventSimulationFormData;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.IHandDisinfectionEventSimulationService;
import org.eclipse.scout.healthcare.shared.person.PersonLookupCall;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = HandDisinfectionEventSimulationFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class HandDisinfectionEventSimulationForm extends AbstractForm {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("SimulateDisinfection");
  }

  @Override
  protected int getConfiguredModalityHint() {
    return IForm.MODALITY_HINT_MODAL;
  }

  public void startNew() {
    startInternal(new NewHandler());
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public DeviceField getDeviceField() {
    return getFieldByClass(DeviceField.class);
  }

  public EmployeeField getEmployeeField() {
    return getFieldByClass(EmployeeField.class);
  }

  public EventInfoBox getEventInfoBox() {
    return getFieldByClass(EventInfoBox.class);
  }

  public DeviceDisplayTextField getDeviceDisplayTextField() {
    return getFieldByClass(DeviceDisplayTextField.class);
  }

  public EmployeeDisplayTextField getEmployeeDisplayTextField() {
    return getFieldByClass(EmployeeDisplayTextField.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  @Order(1000)
  public class MainBox extends AbstractGroupBox {

    @Order(0)
    public class EventInfoBox extends AbstractGroupBox {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Info");
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Order(1000)
      public class DeviceField extends AbstractSmartField<String> {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Device");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }

        @Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return DeviceLookupCall.class;
        }
      }

      @Order(1500)
      public class DeviceDisplayTextField extends AbstractStringField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Device");
        }

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return DeviceField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) {
          setValue(getMasterField().getDisplayText());
        }

        @Override
        protected String execValidateValue(String rawValue) {
          // make sure importFormData does not overwrite the value created from master
          if (StringUtility.isNullOrEmpty(rawValue) && StringUtility.hasText(getMasterField().getDisplayText())) {
            return getValue();
          }
          return super.execValidateValue(rawValue);
        }
      }

      @Order(2000)
      public class EmployeeField extends AbstractSmartField<String> {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Employee");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }

        @Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return PersonLookupCall.class;
        }
      }

      @Order(3000)
      public class EmployeeDisplayTextField extends AbstractStringField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Employee");
        }

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return EmployeeField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) {
          setValue(getMasterField().getDisplayText());
        }

        @Override
        protected String execValidateValue(String rawValue) {
          // make sure importFormData does not overwrite the value created from master
          if (StringUtility.isNullOrEmpty(rawValue) && StringUtility.hasText(getMasterField().getDisplayText())) {
            return getValue();
          }
          return super.execValidateValue(rawValue);
        }
      }
    }

    @Order(100000)
    public class OkButton extends AbstractOkButton {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Start");
      }
    }

    @Order(101000)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() {
      IHandDisinfectionEventSimulationService service = BEANS.get(IHandDisinfectionEventSimulationService.class);
      HandDisinfectionEventSimulationFormData formData = new HandDisinfectionEventSimulationFormData();
      exportFormData(formData);
      formData = service.prepareSimulate(formData);
      importFormData(formData);
    }

    @Override
    protected void execStore() {
    }
  }
}
