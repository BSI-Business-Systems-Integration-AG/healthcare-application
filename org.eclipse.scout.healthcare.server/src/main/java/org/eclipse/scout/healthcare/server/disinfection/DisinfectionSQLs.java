package org.eclipse.scout.healthcare.server.disinfection;

public interface DisinfectionSQLs {

  String CREATE_TABLE = ""
      + "CREATE   TABLE DISINFECTION_EVENT "
      + "         (disinfection_event_id VARCHAR(64) NOT NULL CONSTRAINT DISINFECTION_EVENT_PK PRIMARY KEY, "
      + "          device_id VARCHAR(64), "
      + "          employee_id VARCHAR(64), "
      + "          chemistry VARCHAR(64), "
      + "          evt_start DATE, "
      + "          duration NUMERIC(15), "
      + "          transaction_hash VARCHAR(255), "
      + "          transaction_status NUMERIC(2), "
      + "          disinfection_event_nr NUMERIC(15) "
      + "         ) ";

  String DROP_TABLE = "DROP TABLE DISINFECTION_EVENT ";

  String INSERT = ""
      + "INSERT INTO DISINFECTION_EVENT "
      + "       (disinfection_event_id, "
      + "        device_id, "
      + "        employee_id, "
      + "        chemistry, "
      + "        evt_start, "
      + "        duration, "
      + "        transaction_hash, "
      + "        transaction_status, "
      + "        disinfection_event_nr) "
      + "SELECT :eventId, "
      + "        :deviceId, "
      + "        :employeeId, "
      + "        :chemistry, "
      + "        :eventTimestamp, "
      + "        :duration, "
      + "        :transactionHash, "
      + "        :transactionStatus, "
      + "        :eventNr "
      + "FROM   sysibm.sysdummy1 "
      + "WHERE  1=1 ";

  String AND_NOT_EXISTS_EVENT_ID = "AND NOT EXISTS (SELECT 1 FROM DISINFECTION_EVENT WHERE disinfection_event_id = :eventId) ";

  String UPDATE = ""
      + "UPDATE DISINFECTION_EVENT SET "
      + "       device_id = :deviceId, "
      + "       employee_id = :employeeId, "
      + "       chemistry = :chemistry, "
      + "       evt_start = :eventTimestamp, "
      + "       duration = :duration, "
      + "       transaction_hash = :transactionHash, "
      + "       transaction_status = :transactionStatus, "
      + "       disinfection_event_nr = :eventNr "
      + "WHERE  disinfection_event_id = :eventId ";

  String SELECT_REMAINING = ""
      + "SELECT transaction_hash, "
      + "       transaction_status "
      + "FROM   DISINFECTION_EVENT "
      + "WHERE  disinfection_event_id = :eventId "
      + "INTO   :transactionHash, "
      + "       :transactionStatus ";

}
