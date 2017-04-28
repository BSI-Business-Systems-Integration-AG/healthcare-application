package org.eclipse.scout.healthcare.client.desinfection;

import org.eclipse.scout.healthcare.client.desinfection.DesinfectionTablePage.Table;
import org.eclipse.scout.healthcare.client.ethereum.AbstractTrackOnlineMenu;
import org.eclipse.scout.healthcare.shared.desinfection.DesinfectionTablePageData;
import org.eclipse.scout.healthcare.shared.desinfection.IDesinfectionService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateTimeColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@Data(DesinfectionTablePageData.class)
public class DesinfectionTablePage extends AbstractPageWithTable<Table> {
  private String m_deviceId;
  private String m_personId;

  public String getDeviceId() {
    return m_deviceId;
  }

  public void setDeviceId(String deviceId) {
    m_deviceId = deviceId;
  }

  public String getPersonId() {
    return m_personId;
  }

  public void setPersonId(String personId) {
    m_personId = personId;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("HandDesinfection");
  }

  @Override
  protected void execLoadData(SearchFilter filter) {
    importPageData(BEANS.get(IDesinfectionService.class).getDesinfectionTableData(filter));
  }

  public class Table extends AbstractTable {

    public DeviceColumn getDeviceColumn() {
      return getColumnSet().getColumnByClass(DeviceColumn.class);
    }

    public EmployeeColumn getEmployeeColumn() {
      return getColumnSet().getColumnByClass(EmployeeColumn.class);
    }

    public DateTimeColumn getDateTimeColumn() {
      return getColumnSet().getColumnByClass(DateTimeColumn.class);
    }

    public TrackingUrlColumn getTrackingUrlColumn() {
      return getColumnSet().getColumnByClass(TrackingUrlColumn.class);
    }

    public StatusColumn getStatusColumn() {
      return getColumnSet().getColumnByClass(StatusColumn.class);
    }

    public TransactionHashColumn getTransactionHashColumn() {
      return getColumnSet().getColumnByClass(TransactionHashColumn.class);
    }

    @Order(1000)
    public class TransactionHashColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("TransactionHash");
      }

      @Override
      protected int getConfiguredWidth() {
        return 140;
      }
    }

    @Order(2000)
    public class DeviceColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Device");
      }

      @Override
      protected int getConfiguredWidth() {
        return 120;
      }
    }

    @Order(3000)
    public class EmployeeColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Employee");
      }

      @Override
      protected int getConfiguredWidth() {
        return 120;
      }
    }

    @Order(4000)
    public class DateTimeColumn extends AbstractDateTimeColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("DateTime");
      }

      @Override
      protected int getConfiguredWidth() {
        return 160;
      }
    }

    @Order(5000)
    public class StatusColumn extends AbstractSmartColumn<String> {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Status");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(6000)
    public class TrackingUrlColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("TrackOnline");
      }

      @Override
      protected boolean getConfiguredDisplayable() {
        return false;
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(1000)
    public class TrackOnlineMenu extends AbstractTrackOnlineMenu<TrackingUrlColumn> {

      @Override
      protected TrackingUrlColumn getConfiguredTrackingUrlColumn() {
        return getTrackingUrlColumn();
      }

    }

  }
}
