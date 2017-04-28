package org.eclipse.scout.healthcare.client.device;

import org.eclipse.scout.healthcare.client.device.DeviceTablePage.Table;
import org.eclipse.scout.healthcare.shared.devices.DeviceStatusCodeType;
import org.eclipse.scout.healthcare.shared.devices.DeviceTablePageData;
import org.eclipse.scout.healthcare.shared.devices.IDeviceService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@Data(DeviceTablePageData.class)
public class DeviceTablePage extends AbstractPageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Devices");
  }

  @Override
  protected void execLoadData(SearchFilter filter) {
    importPageData(BEANS.get(IDeviceService.class).getDevicesTableData(filter));
  }

  @Override
  protected IPage<?> execCreateChildPage(ITableRow row) {
    String rowDeviceId = getTable().getDeviceIdColumn().getValue(row);
    DeviceNodePage childPage = new DeviceNodePage(rowDeviceId);
    return childPage;
  }

  public class Table extends AbstractTable {

    public BatchNrColumn getBatchNrColumn() {
      return getColumnSet().getColumnByClass(BatchNrColumn.class);
    }

    public FillLevelColumn getFillLevelColumn() {
      return getColumnSet().getColumnByClass(FillLevelColumn.class);
    }

    public DeviceNameColumn getDeviceNameColumn() {
      return getColumnSet().getColumnByClass(DeviceNameColumn.class);
    }

    public MacAddressColumn getMacAddressColumn() {
      return getColumnSet().getColumnByClass(MacAddressColumn.class);
    }

    public StatusColumn getStatusColumn() {
      return getColumnSet().getColumnByClass(StatusColumn.class);
    }

    public LocationColumn getLocationColumn() {
      return getColumnSet().getColumnByClass(LocationColumn.class);
    }

    public DeviceIdColumn getDeviceIdColumn() {
      return getColumnSet().getColumnByClass(DeviceIdColumn.class);
    }

    @Order(1000)
    public class DeviceIdColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("DeviceID");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(2000)
    public class DeviceNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Name");
      }

      @Override
      protected int getConfiguredWidth() {
        return 120;
      }
    }

    @Order(3000)
    public class MacAddressColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("MacAddress");
      }

      @Override
      protected int getConfiguredWidth() {
        return 140;
      }
    }

    @Order(4000)
    public class LocationColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Location");
      }

      @Override
      protected int getConfiguredWidth() {
        return 220;
      }
    }

    @Order(5000)
    public class BatchNrColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Batch-Nr");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(6000)
    public class StatusColumn extends AbstractSmartColumn<String> {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Status");
      }

      @Override
      protected int getConfiguredWidth() {
        return 140;
      }

      @Override
      protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
        return DeviceStatusCodeType.class;
      }

      @Override
      protected void execDecorateCell(Cell cell, ITableRow row) {
        ICode<String> statusCode = BEANS.get(DeviceStatusCodeType.class).getCode(getValue(row));
        if (null != statusCode) {
          cell.setForegroundColor(statusCode.getForegroundColor());
        }
      }
    }

    @Order(7000)
    public class FillLevelColumn extends AbstractLongColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("FillLevel") + " " + TEXTS.get("inUnit", "%");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }

      @Override
      protected void execDecorateCell(Cell cell, ITableRow row) {
        Long fillLevel = getValue(row);
        if (null != fillLevel) {
          String icon = DeviceFormattingUtility.getFillLevelIconId(fillLevel);
          String fontColor = DeviceFormattingUtility.getFillLevelForegroundColor(fillLevel);

          if (StringUtility.hasText(icon)) {
            cell.setIconId(icon);
          }
          if (StringUtility.hasText(fontColor)) {
            cell.setForegroundColor(fontColor);
          }
        }
      }

      @Override
      protected Long execValidateValue(ITableRow row, Long rawValue) {
        if (null != rawValue) {
          if (rawValue < 0L) {
            rawValue = 0L;
          }
          if (rawValue > 100L) {
            rawValue = 100L;
          }
        }
        return rawValue;
      }
    }

  }
}
