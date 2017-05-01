package org.eclipse.scout.healthcare.server.desinfection.tracker;

import org.eclipse.scout.healthcare.server.desinfection.model.HandDesinfectionEvent;
import org.eclipse.scout.rt.platform.ApplicationScoped;

@ApplicationScoped
public class HandDesinfectionTrackerService {

  public String trackHandDesinfectionEvent(HandDesinfectionEvent event) {
    //TODO [uko] implement
    return null;
  }

  public HandDesinfectionEvent[] getEventsForDevice(String deviceId) {
    //TODO [uko] implement
    return null;
  }

  public HandDesinfectionEvent[] getEventsForEmployee(String employeeId) {
    //TODO [uko] implement
    return null;
  }

}
