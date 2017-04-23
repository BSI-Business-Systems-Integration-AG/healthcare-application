package org.eclipse.scout.healthcare.client.person;

import org.eclipse.scout.healthcare.client.Icons;
import org.eclipse.scout.healthcare.client.person.EmployeePersonTablePage.Table;
import org.eclipse.scout.healthcare.shared.person.EmployeePersonTablePageData;
import org.eclipse.scout.healthcare.shared.person.IPersonService;
import org.eclipse.scout.healthcare.shared.person.OccupationCodeType;
import org.eclipse.scout.healthcare.shared.person.PersonSearchFormData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@Data(EmployeePersonTablePageData.class)
public class EmployeePersonTablePage extends AbstractPersonTablePage<Table> {

  private String m_occupationId;

  public EmployeePersonTablePage(String occupationId) {
    m_occupationId = occupationId;
  }

  @Override
  protected String getConfiguredTitle() {
    ICode<String> occupationCode = BEANS.get(OccupationCodeType.class).getCode(m_occupationId);
    String title = TEXTS.get("Employee");
    if (null != occupationCode) {
      title = occupationCode.getText();
    }
    return title;
  }

  @Override
  protected String getConfiguredIconId() {
    String icon = super.getConfiguredIconId();
    if (OccupationCodeType.DoctorCode.ID.equals(m_occupationId)) {
      icon = Icons.Doctor;
    }
    return icon;
  }

  @Override
  protected void execLoadData(SearchFilter filter) {
    if (null == filter) {
      filter = new SearchFilter();
    }
    PersonSearchFormData formData = (PersonSearchFormData) filter.getFormData();
    if (null == formData) {
      formData = new PersonSearchFormData();
      filter.setFormData(formData);
    }
    formData.getOccupation().setValue(m_occupationId);
    importPageData(BEANS.get(IPersonService.class).getEmployeePersonTableData(filter));
  }

  public class Table extends AbstractPersonTablePage<Table>.Table {
  }
}
