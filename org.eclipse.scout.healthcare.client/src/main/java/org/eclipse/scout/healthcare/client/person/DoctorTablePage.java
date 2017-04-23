package org.eclipse.scout.healthcare.client.person;

import org.eclipse.scout.healthcare.client.Icons;
import org.eclipse.scout.healthcare.shared.person.OccupationCodeType;
import org.eclipse.scout.healthcare.shared.person.PersonSearchFormData;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class DoctorTablePage extends PersonTablePage {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Doctors");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Doctor;
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
    formData.getOccupation().setValue(OccupationCodeType.DoctorCode.ID);

    super.execLoadData(filter);
  }

}
