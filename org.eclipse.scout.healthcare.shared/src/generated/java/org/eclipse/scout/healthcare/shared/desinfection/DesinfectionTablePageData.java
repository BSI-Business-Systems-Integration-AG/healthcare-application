package org.eclipse.scout.healthcare.shared.desinfection;

import java.util.Date;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@Generated(value = "org.eclipse.scout.healthcare.client.desinfection.DesinfectionTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class DesinfectionTablePageData extends AbstractTablePageData {

  private static final long serialVersionUID = 1L;

  @Override
  public DesinfectionTableRowData addRow() {
    return (DesinfectionTableRowData) super.addRow();
  }

  @Override
  public DesinfectionTableRowData addRow(int rowState) {
    return (DesinfectionTableRowData) super.addRow(rowState);
  }

  @Override
  public DesinfectionTableRowData createRow() {
    return new DesinfectionTableRowData();
  }

  @Override
  public Class<? extends AbstractTableRowData> getRowType() {
    return DesinfectionTableRowData.class;
  }

  @Override
  public DesinfectionTableRowData[] getRows() {
    return (DesinfectionTableRowData[]) super.getRows();
  }

  @Override
  public DesinfectionTableRowData rowAt(int index) {
    return (DesinfectionTableRowData) super.rowAt(index);
  }

  public void setRows(DesinfectionTableRowData[] rows) {
    super.setRows(rows);
  }

  public static class DesinfectionTableRowData extends AbstractTableRowData {

    private static final long serialVersionUID = 1L;
    public static final String transactionHash = "transactionHash";
    public static final String device = "device";
    public static final String employee = "employee";
    public static final String dateTime = "dateTime";
    public static final String status = "status";
    public static final String trackingUrl = "trackingUrl";
    private String m_transactionHash;
    private String m_device;
    private String m_employee;
    private Date m_dateTime;
    private String m_status;
    private String m_trackingUrl;

    public String getTransactionHash() {
      return m_transactionHash;
    }

    public void setTransactionHash(String newTransactionHash) {
      m_transactionHash = newTransactionHash;
    }

    public String getDevice() {
      return m_device;
    }

    public void setDevice(String newDevice) {
      m_device = newDevice;
    }

    public String getEmployee() {
      return m_employee;
    }

    public void setEmployee(String newEmployee) {
      m_employee = newEmployee;
    }

    public Date getDateTime() {
      return m_dateTime;
    }

    public void setDateTime(Date newDateTime) {
      m_dateTime = newDateTime;
    }

    public String getStatus() {
      return m_status;
    }

    public void setStatus(String newStatus) {
      m_status = newStatus;
    }

    public String getTrackingUrl() {
      return m_trackingUrl;
    }

    public void setTrackingUrl(String newTrackingUrl) {
      m_trackingUrl = newTrackingUrl;
    }
  }
}