package org.eclipse.scout.healthcare.client.ethereum.smartcontract;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.healthcare.client.common.AbstractDeleteMenu;
import org.eclipse.scout.healthcare.client.common.AbstractEditMenu;
import org.eclipse.scout.healthcare.client.common.AbstractNewMenu;
import org.eclipse.scout.healthcare.client.ethereum.smartcontract.SmartContractAdministrationTablePage.Table;
import org.eclipse.scout.healthcare.shared.ethereum.EthereumClientCodeType;
import org.eclipse.scout.healthcare.shared.ethereum.smartcontract.ISmartContractAdminstrationService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.messagebox.IMessageBox;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBoxes;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.TypeCastUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@Data(SmartContractAdministrationTablePageData.class)
public class SmartContractAdministrationTablePage extends AbstractPageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("SmartContracts");
  }

  @Override
  protected void execLoadData(SearchFilter filter) {
    importPageData(BEANS.get(ISmartContractAdminstrationService.class).loadTableData());
  }

  public class Table extends AbstractTable {

    public AddressColumn getAddressColumn() {
      return getColumnSet().getColumnByClass(AddressColumn.class);
    }

    public OrderBookTypeColumn getOrderBookTypeColumn() {
      return getColumnSet().getColumnByClass(OrderBookTypeColumn.class);
    }

    public EnvironmentColumn getEnvironmentColumn() {
      return getColumnSet().getColumnByClass(EnvironmentColumn.class);
    }

    @Order(1000)
    public class EnvironmentColumn extends AbstractSmartColumn<String> {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Environment");
      }

      @Override
      protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
        return EthereumClientCodeType.class;
      }

      @Override
      protected int getConfiguredWidth() {
        return 150;
      }
    }

    @Order(2000)
    public class OrderBookTypeColumn extends AbstractSmartColumn<String> {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("OrderBookType");
      }

      @Override
      protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
//        return OrderBookTypeCodeType.class;
        return null;
      }

      @Override
      protected int getConfiguredWidth() {
        return 130;
      }
    }

    @Order(3000)
    public class AddressColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("ContractAddress");
      }

      @Override
      protected int getConfiguredWidth() {
        return 380;
      }
    }

    @Order(1000)
    public class NewMenu extends AbstractNewMenu {

      @Override
      protected void execAction() {
        SmartContractAdministrationForm form = new SmartContractAdministrationForm();
        form.startNew();

        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }

    }

    @Order(2000)
    public class EditMenu extends AbstractEditMenu {

      @Override
      protected void execAction() {
        SmartContractAdministrationForm form = new SmartContractAdministrationForm();
        form.getEnvironmentField().setValue(getEnvironmentColumn().getSelectedValue());
        form.getOrderBookTypeField().setValue(getOrderBookTypeColumn().getSelectedValue());
        form.startModify();

        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }

    }

    @Order(3000)
    public class DeleteMenu extends AbstractDeleteMenu {

      @Override
      protected void execAction() {
        int selectionCount = getTable().getSelectedRowCount();
        String body = TEXTS.get("DeleteThisEntry");
        if (selectionCount > 1) {
          body = TEXTS.get("Delete0Entries", TypeCastUtility.castValue(selectionCount, String.class));
        }
        int result = MessageBoxes.createYesNo().withHeader(TEXTS.get("Confirmation")).withBody(body).show();
        if (IMessageBox.YES_OPTION == result) {
          List<ITableRow> selectedRows = getTable().getSelectedRows();
          List<SmartContractAdministrationFormData> formDatas = new ArrayList<SmartContractAdministrationFormData>();
          for (ITableRow row : selectedRows) {
            SmartContractAdministrationFormData formData = new SmartContractAdministrationFormData();
            formData.getEnvironment().setValue(getEnvironmentColumn().getValue(row));
            formData.getOrderBookType().setValue(getOrderBookTypeColumn().getValue(row));
            formDatas.add(formData);
          }
          BEANS.get(ISmartContractAdminstrationService.class).delete(formDatas.toArray(new SmartContractAdministrationFormData[formDatas.size()]));
          reloadPage();
        }
      }

    }

  }

}
