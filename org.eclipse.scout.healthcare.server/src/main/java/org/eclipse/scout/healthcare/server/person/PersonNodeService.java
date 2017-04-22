package org.eclipse.scout.healthcare.server.person;

import org.eclipse.scout.healthcare.shared.person.IPersonNodeService;
import org.eclipse.scout.healthcare.shared.person.PersonNodeTablePageData;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class PersonNodeService implements IPersonNodeService {

  @Override
  public PersonNodeTablePageData getPersonNodeTableData(SearchFilter filter) {
    PersonNodeTablePageData pageData = new PersonNodeTablePageData();
    // TODO [mzi] fill pageData.
    return pageData;
  }
}
