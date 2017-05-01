package org.eclipse.scout.healthcare.client.desinfection.simulation;

import org.eclipse.scout.healthcare.client.desinfection.simulation.HandDesinfectionEventSimulationForm.MainBox.CancelButton;
import org.eclipse.scout.healthcare.client.desinfection.simulation.HandDesinfectionEventSimulationForm.MainBox.EventInfoBox;
import org.eclipse.scout.healthcare.client.desinfection.simulation.HandDesinfectionEventSimulationForm.MainBox.EventInfoBox.DeviceField;
import org.eclipse.scout.healthcare.client.desinfection.simulation.HandDesinfectionEventSimulationForm.MainBox.EventInfoBox.EmployeeField;
import org.eclipse.scout.healthcare.client.desinfection.simulation.HandDesinfectionEventSimulationForm.MainBox.OkButton;
import org.eclipse.scout.healthcare.shared.desinfection.simulation.HandDesinfectionEventSimulationFormData;
import org.eclipse.scout.healthcare.shared.desinfection.simulation.IHandDesinfectionEventSimulationService;
import org.eclipse.scout.healthcare.shared.devices.DeviceLookupCall;
import org.eclipse.scout.healthcare.shared.person.PersonLookupCall;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = HandDesinfectionEventSimulationFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class HandDesinfectionEventSimulationForm extends AbstractForm {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("SimulateDesinfection");
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
      IHandDesinfectionEventSimulationService service = BEANS.get(IHandDesinfectionEventSimulationService.class);
      HandDesinfectionEventSimulationFormData formData = new HandDesinfectionEventSimulationFormData();
      exportFormData(formData);
      //TODO [uko] Set Dirty
      formData = service.prepareSimulate(formData);
      importFormData(formData);
    }

    @Override
    protected void execStore() {
      IHandDesinfectionEventSimulationService service = BEANS.get(IHandDesinfectionEventSimulationService.class);
      HandDesinfectionEventSimulationFormData formData = new HandDesinfectionEventSimulationFormData();
      exportFormData(formData);
      service.simulate(formData);
    }
  }
}
