package org.eclipse.scout.healthcare.client.device;

import org.eclipse.scout.healthcare.client.Icons;
import org.eclipse.scout.healthcare.client.device.DeviceTablePage.Table;
import org.eclipse.scout.healthcare.shared.devices.DeviceStatusCodeType;
import org.eclipse.scout.healthcare.shared.devices.DevicesTablePageData;
import org.eclipse.scout.healthcare.shared.devices.IDevicesService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@Data(DevicesTablePageData.class)
public class DeviceTablePage extends AbstractPageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Devices");
  }

  @Override
  protected void execLoadData(SearchFilter filter) {
    importPageData(BEANS.get(IDevicesService.class).getDevicesTableData(filter));
  }

  public class Table extends AbstractTable {

    public BatchNrColumn getBatchNrColumn() {
      return getColumnSet().getColumnByClass(BatchNrColumn.class);
    }

    public FillLevelColumn getFillLevelColumn() {
      return getColumnSet().getColumnByClass(FillLevelColumn.class);
    }

    public StatusColumn getStatusColumn() {
      return getColumnSet().getColumnByClass(StatusColumn.class);
    }

    public LocationColumn getLocationColumn() {
      return getColumnSet().getColumnByClass(LocationColumn.class);
    }

    public DeviceNrColumn getDeviceNrColumn() {
      return getColumnSet().getColumnByClass(DeviceNrColumn.class);
    }

    @Order(1000)
    public class DeviceNrColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Device-Nr");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(2000)
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

    @Order(3000)
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

    @Order(4000)
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

    @Order(5000)
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
          String icon = null;
          String fontColor = null;
          if (fillLevel >= 0L && fillLevel < 13L) {
            icon = Icons.BatteryEmpty;
            fontColor = "FF6060";
          }
          else if (fillLevel >= 13L && fillLevel < 38L) {
            icon = Icons.BatteryQuater;
            fontColor = "FDAD4F";
          }
          else if (fillLevel >= 38L && fillLevel < 63L) {
            icon = Icons.BatteryHalf;
            fontColor = "0DAF66";
          }
          else if (fillLevel >= 63L && fillLevel < 88L) {
            icon = Icons.BatteryThreeQuater;
            fontColor = "0DAF66";
          }
          else if (fillLevel >= 88L) {
            icon = Icons.BatteryFull;
            fontColor = "0DAF66";
          }

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
