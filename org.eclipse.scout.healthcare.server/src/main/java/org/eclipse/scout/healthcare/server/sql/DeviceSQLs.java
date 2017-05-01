package org.eclipse.scout.healthcare.server.sql;

public interface DeviceSQLs {

  String CALCULATE_FILL_LEVEL = ""
      + " CASE WHEN c.volume_full IS NOT NULL "
      + "   THEN COALESCE(c.volume_current, 0) * 100 / c.volume_full "
      + "   ELSE NULL END ";

  String CREATE_TABLE = ""
      + "CREATE   TABLE DEVICE "
      + "         (device_id VARCHAR(64) NOT NULL CONSTRAINT DEVICE_PK PRIMARY KEY, "
      + "          location VARCHAR(256), "
      + "          batch_nr VARCHAR(64), "
      + "          cartridge_id VARCHAR(64), "
      + "          status VARCHAR(64), "
      + "          mac VARCHAR(32), "
      + "          name VARCHAR(64)"
      + "         ) ";

  String DROP_TABLE = "DROP TABLE DEVICE";

  String CREATE_CARTRIDGE_TABLE = ""
      + "CREATE   TABLE CARTRIDGE "
      + "         (cartridge_id VARCHAR(64) NOT NULL CONSTRAINT CARTRIDGE_PK PRIMARY KEY, "
      + "          tag_id VARCHAR(64), "
      + "          lot_nr VARCHAR(64), "
      + "          chemistry VARCHAR(64), "
      + "          botteling_date DATE, "
      + "          expiration_date DATE, "
      + "          volume_current NUMERIC(15), "
      + "          volume_full NUMERIC(15) DEFAULT 1000000"
      + "         )";

  String DROP_CARTRIDGE_TABLE = "DROP TABLE CARTRIDGE";

  String PAGE_SELECT = ""
      + "SELECT   d.device_id, "
      + "         d.location, "
      + "         d.batch_nr, "
      + "         " + CALCULATE_FILL_LEVEL + ", "
      + "         d.status, "
      + "         d.mac, "
      + "         d.name "
      + "FROM     DEVICE d "
      + "LEFT OUTER JOIN CARTRIDGE c "
      + "ON d.cartridge_id = c.cartridge_id ";

  String PAGE_DATA_SELECT_INTO = ""
      + "INTO     :{page.deviceId}, "
      + "         :{page.location}, "
      + "         :{page.batchNr}, "
      + "         :{page.fillLevel}, "
      + "         :{page.status}, "
      + "         :{page.macAddress}, "
      + "         :{page.deviceName}";

  String LOOKUP = ""
      + "SELECT   device_id, "
      + "         CASE "
      + "           WHEN name IS NULL "
      + "            THEN mac "
      + "           ELSE "
      + "             name || ' (' || mac || ')' "
      + "         END "
      + "FROM     DEVICE "
      + "WHERE    1 = 1 "
      + "<key>    "
      + "AND      device_id = :key "
      + "</key>   "
      + "<text>   "
      + "AND      (UPPER(name) LIKE UPPER('%'||:text||'%') "
      + "          OR UPPER(mac) LIKE UPPER('%'||:text||'%')) "
      + "</text>  "
      + "<all> </all> ";

  String INSERT_SAMPLE = ""
      + "INSERT   INTO DEVICE "
      + "         (device_id, "
      + "          location, "
      + "          batch_nr, "
      + "          cartridge_id, "
      + "          status, "
      + "          mac, "
      + "          name "
      + "         ) "
      + " ";

  String VALUES_01 = ""
      + "VALUES   ('e968c586d09244a4a23163edd1ca43b9', "
      + "          'Level 1 / Wing A / Room 104', "
      + "          'AD-023-0147', "
      + "          'bbf3e9489dd944269fd7882001f262ea', "
      + "          'DEVICESTATUS.READY', "
      + "          '00:80:41:ae:fd:7e', "
      + "          'Roentgen') ";

  String VALUES_02 = ""
      + "VALUES   ('ee01b45091924ae9a3efff057dfd2506', "
      + "          'Level 3 / Wing C / Room 311', "
      + "          'AD-023-0156', "
      + "          'd2d72bfc13c0401d817ee942be643881', "
      + "          'DEVICESTATUS.DESINFECTS', "
      + "          '00:80:41:ae:fd:7f', "
      + "          'Koch') ";

  String VALUES_03 = ""
      + "VALUES   ('6d08d891ab6a46998d2a7aa017065312', "
      + "          'Level 1 / Wing C / Room 197', "
      + "          'AD-017-0071', "
      + "          'a10bf3768eef46f1b803d04cd1334a85', "
      + "          'DEVICESTATUS.OFFLINE',"
      + "          'b8:27:eb:6d:98:9b',"
      + "          'Harvey') ";

  String VALUES_04 = ""
      + "VALUES   ('0b47cb2ec70e457eaecb91e6602e1431', "
      + "          'Level 0 / Wing A / Room 027', "
      + "          'AD-006-0201', "
      + "          '8c8d1a5ed76247a5be45f60480ee13c8', "
      + "          'DEVICESTATUS.REFILLNECESSARY',"
      + "          'b8:27:eb:26:74:fb',"
      + "          'Apgar') ";

  String VALUES_05 = ""
      + "VALUES   ('46d3bc3e9f434e0b81486c46711eec1e', "
      + "          'Level 2 / Wing B / Room 253', "
      + "          'AD-017-0006', "
      + "          'de0061b0a2b24858993dc3d2a8fa6992', "
      + "          'DEVICESTATUS.REFILL', "
      + "          'c6-b7-cb-2e-10-cd',"
      + "          'Pschyrembel') ";

  String INSERT_CARTRIDGE_SAMPLE = ""
      + "INSERT   INTO CARTRIDGE ("
      + "         cartridge_id, "
      + "         tag_id, "
      + "         lot_nr, "
      + "         chemistry, "
      + "         botteling_date, "
      + "         expiration_date, "
      + "         volume_current) "
      + " ";

  String CARTRIDGE_VALUES_01 = ""
      + "VALUES ('a10bf3768eef46f1b803d04cd1334a85', "
      + "         '88000022', "
      + "         '1254399', "
      + "         'Chem 124', "
      + "         DATE('2017-04-27'), "
      + "         DATE('2017-12-17'),"
      + "         525000) ";

  String CARTRIDGE_VALUES_02 = ""
      + "VALUES ('bbf3e9489dd944269fd7882001f262ea', "
      + "         '0484FBF1B70280', "
      + "         '1254401', "
      + "         'Chem 127', "
      + "         DATE('2017-01-25'), "
      + "         DATE('2018-07-15'),"
      + "         970000) ";

  String CARTRIDGE_VALUES_03 = ""
      + "VALUES ('d2d72bfc13c0401d817ee942be643881', "
      + "         '88000011', "
      + "         '1254398', "
      + "         'Chem 125', "
      + "         DATE('2016-12-19'), "
      + "         DATE('2017-12-19'),"
      + "         810000) ";

  String CARTRIDGE_VALUES_04 = ""
      + "VALUES ('8c8d1a5ed76247a5be45f60480ee13c8', "
      + "         '043DEF99742380', "
      + "         '1254400', "
      + "         'Chem 126', "
      + "         DATE('2017-03-01'), "
      + "         DATE('2018-12-28'),"
      + "         260000) ";

  String CARTRIDGE_VALUES_05 = ""
      + "VALUES ('de0061b0a2b24858993dc3d2a8fa6992', "
      + "         'B416E9C45DA63E', "
      + "         '1254411', "
      + "         'Chem 124', "
      + "         DATE('2016-05-08'), "
      + "         DATE('2017-11-21'),"
      + "         60000) ";

  String LOAD = ""
      + " SELECT  d.location, "
      + "         d.batch_nr, "
      + "         d.status,"
      + "         d.mac, "
      + "         d.name, "
      + "         c.cartridge_id, "
      + "         c.tag_id, "
      + "         c.lot_nr, "
      + "         c.chemistry, "
      + "         c.botteling_date, "
      + "         c.expiration_date, "
      + "         " + CALCULATE_FILL_LEVEL + " "
      + " FROM    DEVICE d "
      + " LEFT OUTER JOIN CARTRIDGE c "
      + " ON      d.cartridge_id = c.cartridge_id "
      + " WHERE   d.device_id = :deviceId "
      + " INTO    :location, "
      + "         :batchNr, "
      + "         :status,"
      + "         :macAddress,"
      + "         :deviceName, "
      + "         :cartridgeId, "
      + "         :tagId, "
      + "         :lotNr, "
      + "         :chemistry, "
      + "         :bottelingDateData, "
      + "         :expirationDateData, "
      + "         :fillLevel ";

  String SELECT_RANDOM = ""
      + "SELECT device_id FROM DEVICE ORDER BY RANDOM() INTO :device";

}
