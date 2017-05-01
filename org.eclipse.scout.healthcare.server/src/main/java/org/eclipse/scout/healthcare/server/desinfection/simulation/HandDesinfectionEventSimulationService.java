package org.eclipse.scout.healthcare.server.desinfection.simulation;

import org.eclipse.scout.healthcare.server.sql.DeviceSQLs;
import org.eclipse.scout.healthcare.server.sql.PersonSQLs;
import org.eclipse.scout.healthcare.shared.desinfection.simulation.HandDesinfectionEventSimulationFormData;
import org.eclipse.scout.healthcare.shared.desinfection.simulation.IHandDesinfectionEventSimulationService;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class HandDesinfectionEventSimulationService implements IHandDesinfectionEventSimulationService {

  @Override
  public HandDesinfectionEventSimulationFormData prepareSimulate(HandDesinfectionEventSimulationFormData formData) {
    SQL.selectIntoLimited(DeviceSQLs.SELECT_RANDOM, 1, formData);
    SQL.selectIntoLimited(PersonSQLs.SELECT_RANDOM, 1, formData);
    return formData;
  }

  @Override
  public HandDesinfectionEventSimulationFormData simulate(HandDesinfectionEventSimulationFormData formData) {
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
