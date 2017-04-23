package org.eclipse.scout.healthcare.client.person;

import org.eclipse.scout.healthcare.shared.person.OccupationCodeType;
import org.eclipse.scout.healthcare.shared.person.PersonSearchFormData;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class NursesTablePage extends PersonTablePage {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Nurses");
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
    formData.getOccupation().setValue(OccupationCodeType.NurseCode.ID);

    super.execLoadData(filter);
  }

}
