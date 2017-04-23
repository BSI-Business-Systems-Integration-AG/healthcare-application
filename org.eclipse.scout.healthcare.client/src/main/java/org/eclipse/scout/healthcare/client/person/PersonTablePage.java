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
package org.eclipse.scout.healthcare.client.person;

import org.eclipse.scout.healthcare.client.person.PersonTablePage.Table;
import org.eclipse.scout.healthcare.shared.person.IPersonService;
import org.eclipse.scout.healthcare.shared.person.PersonTablePageData;
import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@PageData(PersonTablePageData.class)
public class PersonTablePage extends AbstractPersonTablePage<Table> {

  @Override
  protected IPage<?> execCreateChildPage(ITableRow row) {
    PersonNodePage childPage = new PersonNodePage();
    childPage.setPersonId(getTable().getPersonIdColumn().getValue(row));
    return childPage;
  }

  @Override
  protected void execLoadData(SearchFilter filter) {
    importPageData(BEANS.get(IPersonService.class)
        .getPersonTableData(filter));
  }

  @Override
  protected boolean getConfiguredLeaf() {
    return false;
  }

  public class Table extends AbstractPersonTablePage<Table>.Table {

  }

}
