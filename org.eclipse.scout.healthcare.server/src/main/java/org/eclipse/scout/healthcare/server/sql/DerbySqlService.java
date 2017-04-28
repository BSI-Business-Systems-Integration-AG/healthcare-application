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
package org.eclipse.scout.healthcare.server.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.scout.healthcare.server.sql.DatabaseProperties.DatabaseAutoCreateProperty;
import org.eclipse.scout.healthcare.server.sql.DatabaseProperties.JdbcMappingNameProperty;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.exception.PlatformExceptionTranslator;
import org.eclipse.scout.rt.server.jdbc.derby.AbstractDerbySqlService;

@Order(1950)
public class DerbySqlService extends AbstractDerbySqlService {

  @Override
  protected String getConfiguredJdbcMappingName() {
    String mappingName = CONFIG.getPropertyValue(JdbcMappingNameProperty.class);

    // add create attribute if we need to autocreate the db
    if (CONFIG.getPropertyValue(DatabaseAutoCreateProperty.class)) {
      return mappingName + ";create=true"; // <1>
    }

    return mappingName;
  }

  public void dropDB() {
    try {
      String jdbcMappingName = CONFIG.getPropertyValue(DatabaseProperties.JdbcMappingNameProperty.class);
      DriverManager.getConnection(jdbcMappingName + ";drop=true");
    }
    catch (SQLException e) {
      BEANS.get(PlatformExceptionTranslator.class).translate(e);
    }
  }

  @Override
  protected void execAfterConnectionCreated(Connection conn) {
    try {
//      Deactivated for building because of duplicate-finder-maven-plugin:
//      [WARNING] Found duplicate (but equal) classes in [org.apache.derby:derby:10.13.1.1, org.apache.derby:derbynet:10.13.1.1]:
//      [WARNING]   org.apache.derby.impl.tools.sysinfo.Main
//      [WARNING]   org.apache.derby.impl.tools.sysinfo.ZipInfoProperties
//      [WARNING]   org.apache.derby.shared.common.error.MessageUtils
//      [WARNING]   org.apache.derby.shared.common.error.ShutdownException
//      [WARNING]   org.apache.derby.shared.common.reference.MessageId
//      [WARNING]   org.apache.derby.tools.sysinfo
//      [WARNING] Found duplicate classes/resources in compile classpath.
//      if (CONFIG.getPropertyValue(DatabaseProperties.EnableNetworkServerControl.class)) {
//        NetworkServerControl nsc = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
//        nsc.start(new PrintWriter(System.out, true));
//      }
    }
    catch (Exception e) {
      BEANS.get(PlatformExceptionTranslator.class).translate(e);
    }
  }
}
