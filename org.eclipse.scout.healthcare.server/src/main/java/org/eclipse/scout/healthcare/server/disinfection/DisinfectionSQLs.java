package org.eclipse.scout.healthcare.server.disinfection;

public interface DisinfectionSQLs {

  String CREATE_TABLE = ""
      + "CREATE   TABLE DISINFECTION_EVENT "
      + "         (disinfection_event_id VARCHAR(64) NOT NULL CONSTRAINT DISINFECTION_EVENT_PK PRIMARY KEY, "
      + "          device_id VARCHAR(64), "
      + "          employee_id VARCHAR(64), "
      + "          cartridge_id VARCHAR(64), "
      + "          evt_start DATE, "
      + "          duration NUMERIC(15), "
      + "          evt_status VARCHAR(32), "
      + "          transaction_hash VARCHAR(255), "
      + "          transaction_status VARCHAR(32), "
      + "          disinfection_event_nr NUMERIC(15) "
      + "         ) ";

  String DROP_TABLE = "DROP TABLE DISINFECTION_EVENT ";

  String SELECT_STATUS_FOR_EVENT_ID = ""
      + "SELECT evt_status "
      + "FROM DISINFECTION_EVENT "
      + "WHERE disinfection_event_id = :eventId ";
}
