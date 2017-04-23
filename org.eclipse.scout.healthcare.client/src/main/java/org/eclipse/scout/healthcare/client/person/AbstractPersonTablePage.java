package org.eclipse.scout.healthcare.client.person;

import java.util.Set;

import org.eclipse.scout.healthcare.client.Icons;
import org.eclipse.scout.healthcare.client.common.CountryLookupCall;
import org.eclipse.scout.healthcare.shared.person.AbstractPersonTablePageData;
import org.eclipse.scout.healthcare.shared.person.OccupationCodeType;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(AbstractPersonTablePageData.class)
public abstract class AbstractPersonTablePage<T extends AbstractPersonTablePage<T>.Table> extends AbstractPageWithTable<AbstractPersonTablePage<T>.Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Persons");
  }

  @Override
  protected String getConfiguredIconId() {
    return AbstractIcons.Person;
  }

  @Override
  protected Class<? extends ISearchForm> getConfiguredSearchForm() {
    return PersonSearchForm.class;
  }

  public class Table extends AbstractTable {

    @Override
    protected String getConfiguredDefaultIconId() {
      return Icons.Person;
    }

    public LastNameColumn getLastNameColumn() {
      return getColumnSet().getColumnByClass(LastNameColumn.class);
    }

    public CountryColumn getCountryColumn() {
      return getColumnSet().getColumnByClass(CountryColumn.class);
    }

    public EmailColumn getEmailColumn() {
      return getColumnSet().getColumnByClass(EmailColumn.class);
    }

    public CityColumn getCityColumn() {
      return getColumnSet().getColumnByClass(CityColumn.class);
    }

    public FirstNameColumn getFirstNameColumn() {
      return getColumnSet().getColumnByClass(FirstNameColumn.class);
    }

    public OccupationColumn getOccupationColumn() {
      return getColumnSet().getColumnByClass(OccupationColumn.class);
    }

    public PersonIdColumn getPersonIdColumn() {
      return getColumnSet().getColumnByClass(PersonIdColumn.class);
    }

    @Order(10)
    public class EditMenu extends AbstractMenu {

      @Override
      protected String getConfiguredIconId() {
        return Icons.Pencil;
      }

      @Override
      protected String getConfiguredKeyStroke() {
        return "alt-e";
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("Edit");
      }

      @Override
      protected void execAction() {
        PersonForm form = new PersonForm();
        form.setPersonId(getPersonIdColumn().getSelectedValue()); // <2>
        form.addFormListener(new PersonFormListener());
        // start the form using its modify handler
        form.startModify();
      }
    }

    @Order(20)
    public class NewMenu extends AbstractMenu {

      @Override
      protected String getConfiguredKeyStroke() {
        return "alt-n";
      }

      @Override
      protected String getConfiguredIconId() {
        // get unicode from http://fontawesome.io/icon/magic/
        return "font:awesomeIcons \uf0d0";
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("New");
      }

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(
            TableMenuType.EmptySpace, TableMenuType.SingleSelection);
      }

      @Override
      protected void execAction() {
        PersonForm form = new PersonForm();
        form.addFormListener(new PersonFormListener());
        // start the form using its new handler
        form.startNew();
      }
    }

    private class PersonFormListener implements FormListener {

      @Override
      public void formChanged(FormEvent e) {
        // reload page to reflect new/changed data after saving any changes
        if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(1000)
    public class PersonIdColumn extends AbstractStringColumn {

      @Override
      protected boolean getConfiguredDisplayable() {
        return false;
      }

      @Override
      protected boolean getConfiguredPrimaryKey() {
        return true;
      }
    }

    @Order(2000)
    public class FirstNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("FirstName");
      }

      @Override
      protected int getConfiguredWidth() {
        return 130;
      }
    }

    @Order(3000)
    public class LastNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("LastName");
      }

      @Override
      protected int getConfiguredWidth() {
        return 130;
      }
    }

    @Order(4000)
    public class CityColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("City");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }

      @Override
      protected int getConfiguredWidth() {
        return 130;
      }
    }

    @Order(5000)
    public class CountryColumn extends AbstractSmartColumn<String> {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Country");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }

      @Override
      protected int getConfiguredWidth() {
        return 130;
      }

      @Override
      protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
        return CountryLookupCall.class;
      }
    }

    @Order(6000)
    public class PhoneColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Phone");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }

      @Override
      protected int getConfiguredWidth() {
        return 130;
      }
    }

    @Order(7000)
    public class MobileColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Mobile");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }

      @Override
      protected int getConfiguredWidth() {
        return 130;
      }
    }

    @Order(8000)
    public class EmailColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Email");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }
    }

    @Order(9000)
    public class OccupationColumn extends AbstractSmartColumn<String> {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Occupation");
      }

      @Override
      protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
        return OccupationCodeType.class;
      }

      @Override
      protected int getConfiguredWidth() {
        return 130;
      }
    }

  }
}
