package org.eclipse.scout.healthcare.server.disinfection;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.scout.healthcare.server.disinfection.model.HandDisinfectionEvent;
import org.eclipse.scout.rt.platform.util.StringUtility;

public class DisinfectionEventPollingSimulationService implements IDisinfectionEventsPollingSerivce {

  private Queue<HandDisinfectionEvent> m_events = new ConcurrentLinkedQueue<HandDisinfectionEvent>();

  public void addEvent(HandDisinfectionEvent event) {
    m_events.add(event);
  }

  public void updateEvent(HandDisinfectionEvent event) {
    for (Iterator<HandDisinfectionEvent> it = m_events.iterator(); it.hasNext();) {
      HandDisinfectionEvent e = it.next();
      if (StringUtility.equalsIgnoreCase(e.getEventId(), event.getEventId())) {
        it.remove();
      }
    }

    addEvent(event);
  }

  @Override
  public HandDisinfectionEvent[] getAllEvents() {
    return m_events.toArray(new HandDisinfectionEvent[m_events.size()]);
  }

}
