package org.eclipse.scout.healthcare.server;

import java.util.concurrent.TimeUnit;

import org.eclipse.scout.healthcare.server.jobs.PollDevicesJob;
import org.eclipse.scout.healthcare.server.jobs.PollDisinfectionEventsJob;
import org.eclipse.scout.healthcare.server.sql.SuperUserRunContextProducer;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ExceptionHandler;
import org.eclipse.scout.rt.platform.job.FixedDelayScheduleBuilder;
import org.eclipse.scout.rt.platform.job.Jobs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobUtility {
  private static final Logger LOG = LoggerFactory.getLogger(JobUtility.class);

  public static void registerJobs() {
    registerPollDisinfectionEventsJob();
    //registerPollDevicesJob();
  }

  private static void registerPollDisinfectionEventsJob() {
    LOG.info("Register job " + PollDisinfectionEventsJob.ID + "");
    Jobs.schedule(new PollDisinfectionEventsJob(),
        Jobs.newInput()
            .withName(PollDisinfectionEventsJob.ID)
            .withRunContext(BEANS.get(SuperUserRunContextProducer.class).produce())
            .withExecutionTrigger(Jobs.newExecutionTrigger()
                .withSchedule(FixedDelayScheduleBuilder.repeatForever(5, TimeUnit.SECONDS)))
            .withExceptionHandling(new ExceptionHandler() {
              @Override
              public void handle(Throwable t) {
                handleException(PollDisinfectionEventsJob.ID, t);
              }
            }, true));
  }

  private static void registerPollDevicesJob() {
    LOG.info("Register job " + PollDevicesJob.ID + "");
    Jobs.schedule(new PollDevicesJob(),
        Jobs.newInput()
            .withName(PollDevicesJob.ID)
            .withRunContext(BEANS.get(SuperUserRunContextProducer.class).produce())
            .withExecutionTrigger(Jobs.newExecutionTrigger()
                .withSchedule(FixedDelayScheduleBuilder.repeatForever(1, TimeUnit.SECONDS)))
            .withExceptionHandling(new ExceptionHandler() {
              @Override
              public void handle(Throwable t) {
                handleException(PollDevicesJob.ID, t);
              }
            }, true));
  }

  private static void handleException(String jobId, Throwable t) {
    LOG.error("Error on execution of job " + jobId + ": ", t);
  }

}
