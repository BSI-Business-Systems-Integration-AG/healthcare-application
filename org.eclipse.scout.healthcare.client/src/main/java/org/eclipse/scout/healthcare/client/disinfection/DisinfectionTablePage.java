package org.eclipse.scout.healthcare.client.disinfection;

import org.eclipse.scout.healthcare.client.disinfection.DisinfectionTablePage.Table;
import org.eclipse.scout.healthcare.client.ethereum.AbstractTrackOnlineMenu;
import org.eclipse.scout.healthcare.shared.disinfection.DisinfectionTablePageData;
import org.eclipse.scout.healthcare.shared.disinfection.IDisinfectionService;
import org.eclipse.scout.healthcare.shared.ethereum.TransactionStatusLookupCall;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateTimeColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(DisinfectionTablePageData.class)
public class DisinfectionTablePage extends AbstractPageWithTable<Table> {
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
    return TEXTS.get("HandDisinfection");
  }

  @Override
  protected void execLoadData(SearchFilter filter) {
    importPageData(BEANS.get(IDisinfectionService.class).getDisinfectionTableData(filter, m_deviceId));
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

    public DurationColumn getDurationColumn() {
      return getColumnSet().getColumnByClass(DurationColumn.class);
    }

    public EventIdColumn getEventIdColumn() {
      return getColumnSet().getColumnByClass(EventIdColumn.class);
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

    @Order(4500)
    public class DurationColumn extends AbstractLongColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Duration") + TEXTS.get("inUnit", "ms");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(5000)
    public class StatusColumn extends AbstractSmartColumn<Integer> {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Status");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }

      @Override
      protected Class<? extends ILookupCall<Integer>> getConfiguredLookupCall() {
        return TransactionStatusLookupCall.class;
      }
    }

    @Order(5500)
    public class EventIdColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Uuid");
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
