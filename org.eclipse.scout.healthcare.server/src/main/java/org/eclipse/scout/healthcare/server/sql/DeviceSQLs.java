package org.eclipse.scout.healthcare.server.sql;

public interface DeviceSQLs {

  String CREATE_TABLE = ""
      + "CREATE   TABLE DEVICE "
      + "         (device_nr VARCHAR(64) NOT NULL CONSTRAINT DEVICE_PK PRIMARY KEY, "
      + "          location VARCHAR(256), "
      + "          batch_nr VARCHAR(64), "
      + "          fill_level NUMERIC(5), "
      + "          status VARCHAR(64) "
      + "         ) ";

  String DROP_TABLE = "DROP TABLE DEVICE";

  String PAGE_SELECT = ""
      + "SELECT   device_nr, "
      + "         location, "
      + "         batch_nr, "
      + "         fill_level, "
      + "         status "
      + "FROM     DEVICE ";

  String PAGE_DATA_SELECT_INTO = ""
      + "INTO     :{page.deviceNr}, "
      + "         :{page.location}, "
      + "         :{page.batchNr}, "
      + "         :{page.fillLevel}, "
      + "         :{page.status} ";

  String INSERT_SAMPLE = ""
      + "INSERT   INTO DEVICE "
      + "         (device_nr, "
      + "          location, "
      + "          batch_nr, "
      + "          fill_level, "
      + "          status "
      + "         ) "
      + " ";

  String VALUES_01 = ""
      + "VALUES   ('D1045', "
      + "          'Level 1 / Wing A / Room 104', "
      + "          'AD-023-0147', "
      + "          97, "
      + "          'DEVICESTATUS.READY') ";

  String VALUES_02 = ""
      + "VALUES   ('D0023', "
      + "          'Level 3 / Wing C / Room 311', "
      + "          'AD-023-0156', "
      + "          81, "
      + "          'DEVICESTATUS.DESINFECTS') ";

  String VALUES_03 = ""
      + "VALUES   ('D3001', "
      + "          'Level 1 / Wing C / Room 197', "
      + "          'AD-017-0071', "
      + "          49, "
      + "          'DEVICESTATUS.OFFLINE') ";

  String VALUES_04 = ""
      + "VALUES   ('D1422', "
      + "          'Level 0 / Wing A / Room 027', "
      + "          'AD-006-0201', "
      + "          26, "
      + "          'DEVICESTATUS.REFILLNECESSARY') ";

  String VALUES_05 = ""
      + "VALUES   ('D2176', "
      + "          'Level 2 / Wing B / Room 253', "
      + "          'AD-017-0006', "
      + "          6, "
      + "          'DEVICESTATUS.REFILL') ";

}
