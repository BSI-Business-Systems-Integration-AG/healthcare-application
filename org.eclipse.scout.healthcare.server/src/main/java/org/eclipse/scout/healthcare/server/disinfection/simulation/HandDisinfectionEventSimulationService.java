package org.eclipse.scout.healthcare.server.disinfection.simulation;

import org.eclipse.scout.healthcare.server.sql.DeviceSQLs;
import org.eclipse.scout.healthcare.server.sql.PersonSQLs;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.HandDisinfectionEventSimulationFormData;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.IHandDisinfectionEventSimulationService;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class HandDisinfectionEventSimulationService implements IHandDisinfectionEventSimulationService {

  @Override
  public HandDisinfectionEventSimulationFormData prepareSimulate(HandDisinfectionEventSimulationFormData formData) {
    SQL.selectIntoLimited(DeviceSQLs.SELECT_RANDOM, 1, formData);
    SQL.selectIntoLimited(PersonSQLs.SELECT_RANDOM, 1, formData);
    return formData;
  }

  @Override
  public HandDisinfectionEventSimulationFormData simulate(HandDisinfectionEventSimulationFormData formData) {
    // TODO [uk] add business logic here.
    Long duration = getRandomDuration();
    return formData;
  }

  private Long getRandomDuration() {
    Long random = Math.abs(NumberUtility.randomLong());
    Long number = 7000L + ((long) (random % (13000L - 7000L)));
    return number;
  }
}
