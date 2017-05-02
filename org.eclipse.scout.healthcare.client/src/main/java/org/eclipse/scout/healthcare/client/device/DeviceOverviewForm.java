package org.eclipse.scout.healthcare.client.device;

import java.io.InputStream;

import org.eclipse.scout.healthcare.client.Icons;
import org.eclipse.scout.healthcare.client.ResourceBase;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.CartridgeBox;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.CartridgeBox.BottelingDateDataField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.CartridgeBox.BottelingDateField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.CartridgeBox.CartridgeIdField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.CartridgeBox.ChemistryField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.CartridgeBox.ExpirationDateDataField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.CartridgeBox.ExpirationDateField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.CartridgeBox.FillLevelField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.CartridgeBox.LotNrField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.CartridgeBox.TagIdField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.DeviceBox;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.DeviceBox.BatchNrField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.DeviceBox.DeviceIdField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.DeviceBox.DeviceNameField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.DeviceBox.LocationField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.DeviceBox.MacAddressField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.DeviceBox.StatusField;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.ImageBox;
import org.eclipse.scout.healthcare.client.device.DeviceOverviewForm.MainBox.DetailsBox.ImageBox.DeviceImageField;
import org.eclipse.scout.healthcare.shared.device.DeviceOverviewFormData;
import org.eclipse.scout.healthcare.shared.devices.DeviceStatusCodeType;
import org.eclipse.scout.healthcare.shared.devices.IDeviceService;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICode;

@FormData(value = DeviceOverviewFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class DeviceOverviewForm extends AbstractForm {

//  private static final Logger LOG = LoggerFactory.getLogger(DeviceOverviewForm.class);

  private String m_deviceId;

  public DeviceOverviewForm(String deviceId) {
    m_deviceId = deviceId;
  }

  @Override
  protected String getConfiguredTitle() {
    String title = TEXTS.get("Devices");
    if (StringUtility.hasText(m_deviceId)) {
      title = m_deviceId;
    }
    return title;
  }

  public void startDefault() {
    startInternal(new DefaultHandler());
  }

  public DetailsBox getDetailsBox() {
    return getFieldByClass(DetailsBox.class);
  }

  public DeviceIdField getDeviceIdField() {
    return getFieldByClass(DeviceIdField.class);
  }

  public DeviceNameField getDeviceNameField() {
    return getFieldByClass(DeviceNameField.class);
  }

  public LocationField getLocationField() {
    return getFieldByClass(LocationField.class);
  }

  public MacAddressField getMacAddressField() {
    return getFieldByClass(MacAddressField.class);
  }

  public BatchNrField getBatchNrField() {
    return getFieldByClass(BatchNrField.class);
  }

  public FillLevelField getFillLevelField() {
    return getFieldByClass(FillLevelField.class);
  }

  public DeviceBox getDeviceBox() {
    return getFieldByClass(DeviceBox.class);
  }

  public ImageBox getImageBox() {
    return getFieldByClass(ImageBox.class);
  }

  public DeviceImageField getDeviceImageField() {
    return getFieldByClass(DeviceImageField.class);
  }

  public CartridgeBox getCartridgeBox() {
    return getFieldByClass(CartridgeBox.class);
  }

  public ChemistryField getChemistryField() {
    return getFieldByClass(ChemistryField.class);
  }

  public BottelingDateField getBottelingDateField() {
    return getFieldByClass(BottelingDateField.class);
  }

  public ExpirationDateField getExpirationDateField() {
    return getFieldByClass(ExpirationDateField.class);
  }

  public CartridgeIdField getCartridgeIdField() {
    return getFieldByClass(CartridgeIdField.class);
  }

  public LotNrField getLotNrField() {
    return getFieldByClass(LotNrField.class);
  }

  public TagIdField getTagIdField() {
    return getFieldByClass(TagIdField.class);
  }

  public BottelingDateDataField getBottelingDateDataField() {
    return getFieldByClass(BottelingDateDataField.class);
  }

  public ExpirationDateDataField getExpirationDateDataField() {
    return getFieldByClass(ExpirationDateDataField.class);
  }

  public StatusField getStatusField() {
    return getFieldByClass(StatusField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(1000)
  public class MainBox extends AbstractGroupBox {

    private static final int OVERVIW_LABEL_WIDTH = 160;

    @Order(1000)
    public class DetailsBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Details");
      }

      @Order(1000)
      public class DeviceBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Device");
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Order(1000)
        public class DeviceNameField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DeviceName");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }

          @Override
          protected String getConfiguredFont() {
            return "BOLD";
          }

          @Override
          protected String getConfiguredLabelFont() {
            return "BOLD";
          }
        }

        @Order(2000)
        public class StatusField extends AbstractLabelField {
          private ICode<String> m_code;

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Status");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }

          @Override
          protected String getConfiguredFont() {
            return "BOLD";
          }

          @Override
          protected String getConfiguredLabelFont() {
            return "BOLD";
          }

          @Override
          protected String execValidateValue(String value) {
            ICode<String> statusCode = BEANS.get(DeviceStatusCodeType.class).getCode(value);
            String formattedValue = "";
            if (null != statusCode) {
              m_code = statusCode;
              formattedValue = statusCode.getText();
            }
            else {
              m_code = null;
              formattedValue = value;
            }
            return formattedValue;
          }

          @Override
          protected String execFormatValue(String value) {
            if (null != m_code) {
              setForegroundColor(m_code.getForegroundColor());
            }
            else {
              setForegroundColor(null);
            }

            return value;
          }
        }

        @Order(3000)
        public class LocationField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Location");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }
        }

        @Order(4000)
        public class DeviceIdField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DeviceID");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }
        }

        @Order(5000)
        public class MacAddressField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("MacAddress");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }
        }

        @Order(6000)
        public class BatchNrField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Batch-Nr");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }
        }

      }

      @Order(2000)
      public class ImageBox extends AbstractGroupBox {

        public static final String DISINFECTION_DEVICE = "images/disinfection_device.png";
        public static final String DISINFECTION_DEVICE_FILENAME = "disinfection_device.png";

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Image");
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Order(2000)
        public class DeviceImageField extends AbstractImageField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Device");
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected int getConfiguredGridH() {
            return 6;
          }

          @Override
          protected boolean getConfiguredAutoFit() {
            return true;
          }

          @Override
          protected void execInitField() {
            clearErrorStatus();

            try (InputStream in = ResourceBase.class.getResourceAsStream(DISINFECTION_DEVICE)) {
              setImage(IOUtility.readBytes(in));
              setImageId(DISINFECTION_DEVICE_FILENAME);
            }
            catch (Exception e) {
              e.printStackTrace();
              addErrorStatus(e.getMessage());
            }
          }

        }
      }

      @Order(3000)
      public class CartridgeBox extends AbstractGroupBox {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Cartridge");
        }

        @Order(1000)
        public class ChemistryField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Chemistry");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }
        }

        @Order(1500)
        public class BottelingDateDataField extends AbstractDateField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DateOfBotteling");
          }

          @Override
          protected boolean getConfiguredVisible() {
            return false;
          }

        }

        @Order(2000)
        public class BottelingDateField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DateOfBotteling");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }

          @Override
          protected Class<? extends IValueField> getConfiguredMasterField() {
            return BottelingDateDataField.class;
          }

          @Override
          protected void execChangedMasterValue(Object newMasterValue) {
            String bottelingDate = getMasterField().getDisplayText();
            setValue(bottelingDate);
          }
        }

        @Order(2500)
        public class ExpirationDateDataField extends AbstractDateField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("ExpirationDate");
          }

          @Override
          protected boolean getConfiguredVisible() {
            return false;
          }
        }

        @Order(3000)
        public class ExpirationDateField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("ExpirationDate");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }

          @Override
          protected Class<? extends IValueField> getConfiguredMasterField() {
            return ExpirationDateDataField.class;
          }

          @Override
          protected void execChangedMasterValue(Object newMasterValue) {
            String expirationDate = getMasterField().getDisplayText();
            setValue(expirationDate);
          }
        }

        @Order(4000)
        public class FillLevelField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("FillLevel");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }

          @Override
          protected String getConfiguredFont() {
            return "BOLD";
          }

          @Override
          protected String getConfiguredLabelFont() {
            return "BOLD";
          }

          @Override
          protected String execFormatValue(String rawValue) {
            Long fillLevel = null;
            try {
              fillLevel = NumberUtility.parseLong(rawValue);
            }
            catch (NumberFormatException e) {
              // TODO: nop
            }
            if (null != fillLevel) {
              String fontColor = DeviceFormattingUtility.getFillLevelForegroundColor(fillLevel);
              if (StringUtility.hasText(fontColor)) {
                setForegroundColor(fontColor);
              }
            }
            else {
              setForegroundColor(null);
            }

            return rawValue + " %";
          }

          @Override
          protected String execValidateValue(String rawValue) {
            Long fillLevel = null;
            String fillLevelString = "";
            try {
              fillLevel = NumberUtility.parseLong(rawValue);
            }
            catch (NumberFormatException e) {
              // TODO: nop
            }

            if (null != fillLevel) {
              if (fillLevel < 0L) {
                fillLevel = 0L;
              }
              if (fillLevel > 100L) {
                fillLevel = 100L;
              }
              fillLevelString = fillLevel.toString();
            }

            return fillLevelString;
          }

        }

        @Order(5000)
        public class CartridgeIdField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("CartridgeID");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }
        }

        @Order(6000)
        public class LotNrField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Lot-Nr");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }
        }

        @Order(7000)
        public class TagIdField extends AbstractLabelField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("TagID");
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return OVERVIW_LABEL_WIDTH;
          }
        }

      }

    }

    @Order(1000)
    public class ReloadMenu extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return TEXTS.get("Reload");
      }

      @Override
      protected String getConfiguredIconId() {
        return Icons.RotateRight;
      }

      @Override
      protected void execAction() {
        getHandler().onLoad();
      }
    }

  }

  public class DefaultHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() {
      DeviceOverviewFormData formData = BEANS.get(IDeviceService.class).load(m_deviceId);
      importFormData(formData);
    }

    @Override
    protected void execStore() {
    }
  }
}
