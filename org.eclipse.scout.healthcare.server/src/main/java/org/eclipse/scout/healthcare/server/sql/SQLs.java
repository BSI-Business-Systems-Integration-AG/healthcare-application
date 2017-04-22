package org.eclipse.scout.healthcare.server.sql;

public interface SQLs {

  String SELECT_TABLE_NAMES = ""
      + "SELECT   UPPER(tablename) "
      + "FROM     sys.systables "
      + "INTO     :result";

  String AND_LIKE_CAUSE = "AND LOWER(%s) LIKE LOWER(:%s || '%%') ";

}
