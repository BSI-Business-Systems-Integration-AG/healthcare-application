package org.eclipse.scout.healthcare.shared.order;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@Generated(value = "org.eclipse.scout.healthcare.client.order.OwnDealsTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class OwnDealsTablePageData extends AbstractDealsTablePageData {

  private static final long serialVersionUID = 1L;

  @Override
  public OwnDealsTableRowData addRow() {
    return (OwnDealsTableRowData) super.addRow();
  }

  @Override
  public OwnDealsTableRowData addRow(int rowState) {
    return (OwnDealsTableRowData) super.addRow(rowState);
  }

  @Override
  public OwnDealsTableRowData createRow() {
    return new OwnDealsTableRowData();
  }

  @Override
  public Class<? extends AbstractTableRowData> getRowType() {
    return OwnDealsTableRowData.class;
  }

  @Override
  public OwnDealsTableRowData[] getRows() {
    return (OwnDealsTableRowData[]) super.getRows();
  }

  @Override
  public OwnDealsTableRowData rowAt(int index) {
    return (OwnDealsTableRowData) super.rowAt(index);
  }

  public void setRows(OwnDealsTableRowData[] rows) {
    super.setRows(rows);
  }

  public static class OwnDealsTableRowData extends AbstractDealsTableRowData {

    private static final long serialVersionUID = 1L;
  }
}
