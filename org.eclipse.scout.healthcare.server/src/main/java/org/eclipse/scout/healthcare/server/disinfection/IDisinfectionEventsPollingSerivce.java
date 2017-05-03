package org.eclipse.scout.healthcare.server.disinfection;

import org.eclipse.scout.healthcare.server.disinfection.model.HandDisinfectionEvent;
import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.service.IService;

@ApplicationScoped
public interface IDisinfectionEventsPollingSerivce extends IService {

  HandDisinfectionEvent[] getAllEvents();

}
