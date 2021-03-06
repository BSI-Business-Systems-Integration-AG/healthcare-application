package org.eclipse.scout.healthcare.server.sql;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.eclipse.scout.healthcare.server.disinfection.DisinfectionSQLs;
import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.CreateImmediately;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.context.RunContext;
import org.eclipse.scout.rt.platform.exception.ExceptionHandler;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringArrayHolder;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// tag::service[]
@ApplicationScoped
@CreateImmediately
public class DatabaseSetupService implements IDataStoreService {
  private static final Logger LOG = LoggerFactory.getLogger(DatabaseSetupService.class);

  @PostConstruct
  public void autoCreateDatabase() {
    if (CONFIG.getPropertyValue(DatabaseProperties.DatabaseAutoCreateProperty.class)) {
      try {
        RunContext context = BEANS.get(SuperUserRunContextProducer.class).produce();
        IRunnable runnable = new IRunnable() {

          @Override
          public void run() throws Exception {
            createDataStore();
          }
        };

        context.run(runnable);
      }
      catch (RuntimeException e) {
        BEANS.get(ExceptionHandler.class).handle(e);
      }
    }
  }

  public void createPersonTable() {
    if (!getExistingTables().contains("PERSON")) {
      SQL.insert(PersonSQLs.CREATE_TABLE);
      LOG.info("Database table 'PERSON' created");

      if (CONFIG.getPropertyValue(DatabaseProperties.DatabaseAutoPopulateProperty.class)) {
        SQL.insert(PersonSQLs.INSERT_SAMPLE + PersonSQLs.VALUES_DOCTOR_01);
        SQL.insert(PersonSQLs.INSERT_SAMPLE + PersonSQLs.VALUES_DOCTOR_02);
        SQL.insert(PersonSQLs.INSERT_SAMPLE + PersonSQLs.VALUES_DOCTOR_03);

        SQL.insert(PersonSQLs.INSERT_SAMPLE + PersonSQLs.VALUES_NURSE_01);
        SQL.insert(PersonSQLs.INSERT_SAMPLE + PersonSQLs.VALUES_NURSE_02);
        SQL.insert(PersonSQLs.INSERT_SAMPLE + PersonSQLs.VALUES_NURSE_03);
        LOG.info("Database table 'PERSON' populated with sample data");
      }
    }
  }

  public void createDeviceTable() {
    if (!getExistingTables().contains("DEVICE")) {
      SQL.insert(DeviceSQLs.CREATE_TABLE);
      LOG.info("Database table 'DEVICE' created");
      if (CONFIG.getPropertyValue(DatabaseProperties.DatabaseAutoPopulateProperty.class)) {
        SQL.insert(DeviceSQLs.INSERT_SAMPLE + DeviceSQLs.VALUES_01);
        SQL.insert(DeviceSQLs.INSERT_SAMPLE + DeviceSQLs.VALUES_02);
        SQL.insert(DeviceSQLs.INSERT_SAMPLE + DeviceSQLs.VALUES_03);
        SQL.insert(DeviceSQLs.INSERT_SAMPLE + DeviceSQLs.VALUES_04);
        SQL.insert(DeviceSQLs.INSERT_SAMPLE + DeviceSQLs.VALUES_05);
        LOG.info("Database table 'DEVICE' populated with sample data");
      }
    }
  }

  public void createCartridgeTable() {
    if (!getExistingTables().contains("CARTRIDGE")) {
      SQL.insert(DeviceSQLs.CREATE_CARTRIDGE_TABLE);
      LOG.info("Database table 'CARTRIDGE' created");
      if (CONFIG.getPropertyValue(DatabaseProperties.DatabaseAutoPopulateProperty.class)) {
        SQL.insert(DeviceSQLs.INSERT_CARTRIDGE_SAMPLE + DeviceSQLs.CARTRIDGE_VALUES_01);
        SQL.insert(DeviceSQLs.INSERT_CARTRIDGE_SAMPLE + DeviceSQLs.CARTRIDGE_VALUES_02);
        SQL.insert(DeviceSQLs.INSERT_CARTRIDGE_SAMPLE + DeviceSQLs.CARTRIDGE_VALUES_03);
        SQL.insert(DeviceSQLs.INSERT_CARTRIDGE_SAMPLE + DeviceSQLs.CARTRIDGE_VALUES_04);
        SQL.insert(DeviceSQLs.INSERT_CARTRIDGE_SAMPLE + DeviceSQLs.CARTRIDGE_VALUES_05);
        LOG.info("Database table 'CARTRIDGE' populated with sample data");
      }
    }
  }

  public void createDisinfectionEventTable() {
    if (!getExistingTables().contains("DISINFECTION_EVENT")) {
      SQL.insert(DisinfectionSQLs.CREATE_TABLE);
      LOG.info("Database table 'DISINFECTION_EVENT' created");
    }
  }

  private Set<String> getExistingTables() {
    StringArrayHolder tables = new StringArrayHolder();
    SQL.selectInto(SQLs.SELECT_TABLE_NAMES, new NVPair("result", tables));
    return CollectionUtility.hashSet(tables.getValue());
  }

  @Override
  public void dropDataStore() {
    SQL.update(PersonSQLs.DROP_TABLE);
    SQL.update(DeviceSQLs.DROP_TABLE);
    SQL.update(DeviceSQLs.DROP_CARTRIDGE_TABLE);
    SQL.update(DisinfectionSQLs.DROP_TABLE);
  }

  @Override
  public void createDataStore() {
    createPersonTable();
    createDeviceTable();
    createCartridgeTable();
    createDisinfectionEventTable();
  }
}
