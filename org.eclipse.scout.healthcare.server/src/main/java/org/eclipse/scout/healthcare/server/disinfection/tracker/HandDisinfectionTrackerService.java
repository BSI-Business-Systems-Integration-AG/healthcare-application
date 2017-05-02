package org.eclipse.scout.healthcare.server.disinfection.tracker;

import org.eclipse.scout.healthcare.server.disinfection.model.HandDisinfectionEvent;
import org.eclipse.scout.rt.platform.ApplicationScoped;

@ApplicationScoped
public class HandDisinfectionTrackerService {

  public String trackHandDisinfectionEvent(HandDisinfectionEvent event) {
    //TODO [uko] implement
    return null;
  }

  public HandDisinfectionEvent[] getEventsForDevice(String deviceId) {
    //TODO [uko] implement
    return null;
  }

  public HandDisinfectionEvent[] getEventsForEmployee(String employeeId) {
    //TODO [uko] implement
    return null;
  }

}
