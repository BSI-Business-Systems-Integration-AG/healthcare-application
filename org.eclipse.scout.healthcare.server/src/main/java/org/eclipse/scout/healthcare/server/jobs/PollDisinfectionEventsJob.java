package org.eclipse.scout.healthcare.server.jobs;

import org.eclipse.scout.healthcare.shared.disinfection.IDisinfectionService;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;

public class PollDisinfectionEventsJob implements IRunnable {

  public static final String ID = "DisinfectionService.PollDisinfectionEvents";

  @Override
  public void run() throws Exception {
    BEANS.get(IDisinfectionService.class).pollDisinfectionEvents();
  }

}
