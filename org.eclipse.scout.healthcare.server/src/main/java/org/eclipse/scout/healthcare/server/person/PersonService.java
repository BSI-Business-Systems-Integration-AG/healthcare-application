/*******************************************************************************
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.healthcare.server.person;

import java.util.UUID;

import org.eclipse.scout.healthcare.server.sql.PersonSQLs;
import org.eclipse.scout.healthcare.server.sql.SQLs;
import org.eclipse.scout.healthcare.shared.person.EmployeePersonTablePageData;
import org.eclipse.scout.healthcare.shared.person.IPersonService;
import org.eclipse.scout.healthcare.shared.person.PersonCreatePermission;
import org.eclipse.scout.healthcare.shared.person.PersonFormData;
import org.eclipse.scout.healthcare.shared.person.PersonReadPermission;
import org.eclipse.scout.healthcare.shared.person.PersonSearchFormData;
import org.eclipse.scout.healthcare.shared.person.PersonTablePageData;
import org.eclipse.scout.healthcare.shared.person.PersonUpdatePermission;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

public class PersonService implements IPersonService {

  @Override
  public PersonTablePageData getPersonTableData(SearchFilter filter) {
    PersonTablePageData pageData = new PersonTablePageData();

    StringBuilder sql = new StringBuilder();

    sql.append(PersonSQLs.PAGE_SELECT);
    sql.append(" WHERE 1 = 1 ");

    PersonSearchFormData searchData = addSearchFilter(sql, filter);

    sql.append(PersonSQLs.PAGE_DATA_SELECT_INTO);

    SQL.selectInto(sql.toString(), searchData, new NVPair("page", pageData));

    return pageData;
  }

  @Override
  public EmployeePersonTablePageData getEmployeePersonTableData(SearchFilter filter) {
    EmployeePersonTablePageData pageData = new EmployeePersonTablePageData();

    StringBuilder sql = new StringBuilder();

    sql.append(PersonSQLs.PAGE_SELECT);
    sql.append(" WHERE 1 = 1 ");

    PersonSearchFormData searchData = addSearchFilter(sql, filter);

    sql.append(PersonSQLs.PAGE_DATA_SELECT_INTO);

    SQL.selectInto(sql.toString(), searchData, new NVPair("page", pageData));

    return pageData;
  }

  protected PersonSearchFormData addSearchFilter(StringBuilder sql, SearchFilter filter) {
    PersonSearchFormData searchData = (PersonSearchFormData) filter.getFormData();
    if (searchData != null) {
      addToWhere(sql, searchData.getFirstName().getValue(), "first_name", "firstName");
      addToWhere(sql, searchData.getLastName().getValue(), "last_name", "lastName");
      addToWhere(sql, searchData.getLocation().getCity().getValue(), "city", "location.city");
      addToWhere(sql, searchData.getLocation().getCountry().getValue(), "country", "location.country");
      addToWhere(sql, searchData.getOccupation().getValue(), "occupation_id", "occupation");
    }
    return searchData;
  }

  protected void addToWhere(StringBuilder sqlWhere, String fieldValue, String sqlAttribute, String searchAttribute) {
    if (StringUtility.hasText(fieldValue)) {
      sqlWhere.append(String.format(SQLs.AND_LIKE_CAUSE, sqlAttribute, searchAttribute));
    }
  }

  @Override
  public PersonFormData create(PersonFormData formData) {
    if (!ACCESS.check(new PersonCreatePermission())) {
      throw new VetoException(TEXTS.get("InsufficientPrivileges"));
    }

    if (StringUtility.isNullOrEmpty(formData.getPersonId())) {
      formData.setPersonId(UUID.randomUUID().toString());
    }

    SQL.insert(PersonSQLs.INSERT, formData);

    return store(formData);
  }

  @Override
  public PersonFormData load(PersonFormData formData) {
    if (!ACCESS.check(new PersonReadPermission())) {
      throw new VetoException(TEXTS.get("InsufficientPrivileges"));
    }

    SQL.selectInto(PersonSQLs.SELECT, formData);

    return formData;
  }

  @Override
  public PersonFormData store(PersonFormData formData) {
    if (!ACCESS.check(new PersonUpdatePermission())) {
      throw new VetoException(TEXTS.get("InsufficientPrivileges"));
    }

    SQL.update(PersonSQLs.UPDATE, formData);

    return formData;
  }
}
// end::all[]
// end::getTableData[]
