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

import javax.security.auth.Subject;

import org.eclipse.scout.rt.platform.config.AbstractBooleanConfigProperty;
import org.eclipse.scout.rt.platform.config.AbstractStringConfigProperty;
import org.eclipse.scout.rt.platform.config.AbstractSubjectConfigProperty;

public class DatabaseProperties {

  public static class DatabaseAutoCreateProperty extends AbstractBooleanConfigProperty {
    // defines default value and key

    @Override
    protected Boolean getDefaultValue() {
      return Boolean.FALSE;
    }

    @Override
    public String getKey() {
      return "healthcare.database.autocreate";
    }
  }

  public static class DatabaseAutoPopulateProperty extends AbstractBooleanConfigProperty {

    @Override
    protected Boolean getDefaultValue() {
      return Boolean.FALSE;
    }

    @Override
    public String getKey() {
      return "healthcare.database.autopopulate";
    }
  }

  public static class JdbcMappingNameProperty extends AbstractStringConfigProperty {

    @Override
    protected String getDefaultValue() {
      return "jdbc:derby:memory:healthcare-database";
    }

    @Override
    public String getKey() {
      return "healthcare.database.jdbc.mapping.name";
    }
  }

  public static class SuperUserSubjectProperty extends AbstractSubjectConfigProperty {

    @Override
    protected Subject getDefaultValue() {
      return convertToSubject("system");
    }

    @Override
    public String getKey() {
      return "healthcare.superuser";
    }
  }

  public static class EnableNetworkServerControl extends AbstractBooleanConfigProperty {
    @Override
    protected Boolean getDefaultValue() {
      return Boolean.FALSE;
    }

    @Override
    public String getKey() {
      return "healthcare.database.networkservercontrol.enabled";
    }
  }
}
