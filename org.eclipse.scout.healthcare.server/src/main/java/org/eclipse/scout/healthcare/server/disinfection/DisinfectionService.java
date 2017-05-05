package org.eclipse.scout.healthcare.server.disinfection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.eclipse.scout.healthcare.server.disinfection.model.HandDisinfectionEvent;
import org.eclipse.scout.healthcare.server.disinfection.tracker.HandDisinfectionEventTrackerService;
import org.eclipse.scout.healthcare.server.ethereum.EthereumService;
import org.eclipse.scout.healthcare.server.ethereum.model.Transaction;
import org.eclipse.scout.healthcare.server.sql.SuperUserRunContextProducer;
import org.eclipse.scout.healthcare.shared.disinfection.DisinfectionTablePageData;
import org.eclipse.scout.healthcare.shared.disinfection.DisinfectionTablePageData.DisinfectionTableRowData;
import org.eclipse.scout.healthcare.shared.disinfection.IDisinfectionService;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ExceptionHandler;
import org.eclipse.scout.rt.platform.exception.PlatformException;
import org.eclipse.scout.rt.platform.holders.DateArrayHolder;
import org.eclipse.scout.rt.platform.holders.IntegerArrayHolder;
import org.eclipse.scout.rt.platform.holders.LongArrayHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringArrayHolder;
import org.eclipse.scout.rt.platform.job.Jobs;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisinfectionService implements IDisinfectionService {

  private static final Logger LOG = LoggerFactory.getLogger(DisinfectionService.class);

  @Override
  public DisinfectionTablePageData getDisinfectionTableData(SearchFilter filter) {
    return getDisinfectionTableData(filter, null);
  }

  @Override
  public DisinfectionTablePageData getDisinfectionTableData(SearchFilter filter, String deviceId) {
    DisinfectionTablePageData pageData = new DisinfectionTablePageData();
    HandDisinfectionEventTrackerService service = BEANS.get(HandDisinfectionEventTrackerService.class);
    HandDisinfectionEvent[] events = service.getAllHandDisinfectionEvents();
    events = loadRemainingEventsFromDatabase(events);
    String contractAddress = service.getContractAddress();
    String trackingUrl = "";
    if (StringUtility.hasText(contractAddress)) {
      trackingUrl = BEANS.get(EthereumService.class).getEtherscanIoTrackingUrl(contractAddress, "address");
    }
    for (HandDisinfectionEvent e : events) {
      e = loadRemainingInfoForEvent(e);
      if (StringUtility.hasText(e.getEventId())) {
        if (StringUtility.isNullOrEmpty(deviceId) || StringUtility.equalsIgnoreCase(e.getDeviceId(), deviceId)) {
          DisinfectionTableRowData row = pageData.addRow();
          row.setDevice(e.getDeviceId());
          row.setEmployee(e.getEmployeeId());
          row.setTransactionHash(e.getTransactionHash());
          row.setDateTime(e.getEventTimestamp());
          row.setDuration(e.getDuration());
          row.setEventId(e.getEventId());
          row.setStatus(e.getTransactionStatus());
          row.setTrackingUrl(trackingUrl);
        }
      }
    }
    return pageData;
  }

  @Override
  public void pollDisinfectionEvents() {
    HandDisinfectionEvent[] events = BEANS.get(IDisinfectionEventsPollingSerivce.class).getAllEvents();
    for (HandDisinfectionEvent event : events) {
      try {
        addAndTrackDisinfectionEvent(event);
      }
      catch (PlatformException e) {
        // nop: event already polled, saved and tracked
      }
      catch (Exception e) {
        LOG.error("Event could not be tracked. " + event.toString(), e);
      }
    }
  }

  public void addAndTrackDisinfectionEvent(HandDisinfectionEvent event) {
    int insert = SQL.insert(DisinfectionSQLs.INSERT, event);
    if (insert == 1) {
      Jobs.schedule(new TrackDisinfectionEventJob(event),
          Jobs.newInput()
              .withName("trackDisinfectionEvent_" + event.getEventId())
              .withRunContext(BEANS.get(SuperUserRunContextProducer.class).produce())
              .withExceptionHandling(new ExceptionHandler() {
                @Override
                public void handle(Throwable t) {
                  LOG.error("Event could not be tracked. " + event.toString(), t);
                }
              }, true));
    }
    if (StringUtility.isNullOrEmpty(event.getTransactionHash())) {
      LOG.error("Event could not be tracked. " + event.toString());
    }
  }

  private HandDisinfectionEvent[] loadRemainingEventsFromDatabase(HandDisinfectionEvent[] events) {
    StringBuilder notIn = new StringBuilder();
    for (HandDisinfectionEvent event : events) {
      if (StringUtility.hasText(notIn.toString())) {
        notIn.append(", ");
      }
      notIn.append(event.getEventId());
    }
    StringBuilder sql = new StringBuilder(DisinfectionSQLs.SELECT_ALL);
    if (StringUtility.hasText(notIn.toString())) {
      sql.append(DisinfectionSQLs.CONDITION_NOT_IN_EVENT_IDS);
    }
    sql.append(DisinfectionSQLs.INTO_ALL);

    StringArrayHolder eventIdHolder = new StringArrayHolder();
    StringArrayHolder deviceIdHolder = new StringArrayHolder();
    StringArrayHolder employeeIdHolder = new StringArrayHolder();
    StringArrayHolder chemistryHolder = new StringArrayHolder();
    DateArrayHolder eventTimestampHolder = new DateArrayHolder();
    LongArrayHolder durationHolder = new LongArrayHolder();
    StringArrayHolder transactionHashHolder = new StringArrayHolder();
    IntegerArrayHolder transactionStatusHolder = new IntegerArrayHolder();
    LongArrayHolder eventNrHolder = new LongArrayHolder();

    SQL.selectInto(sql.toString(),
        new NVPair("eventIds", notIn.toString()),
        new NVPair("eventId", eventIdHolder),
        new NVPair("deviceId", deviceIdHolder),
        new NVPair("employeeId", employeeIdHolder),
        new NVPair("chemistry", chemistryHolder),
        new NVPair("eventTimestamp", eventTimestampHolder),
        new NVPair("duration", durationHolder),
        new NVPair("transactionHash", transactionHashHolder),
        new NVPair("transactionStatus", transactionStatusHolder),
        new NVPair("eventNr", eventNrHolder));

    int length = eventIdHolder.getValue().length;
    if (length > 0) {
      List<HandDisinfectionEvent> eventList = new ArrayList<HandDisinfectionEvent>(Arrays.asList(events));
      for (int i = 0; i < length; i++) {
        try {
          String eventId = eventIdHolder.getValue()[i];
          String deviceId = deviceIdHolder.getValue()[i];
          String employeeId = employeeIdHolder.getValue()[i];
          String chemistry = chemistryHolder.getValue()[i];
          Date eventTimestamp = eventTimestampHolder.getValue()[i];
          Long duration = durationHolder.getValue()[i];
          String transactionHash = transactionHashHolder.getValue()[i];
          Integer transactionStatus = transactionStatusHolder.getValue()[i];
          Long eventNr = eventNrHolder.getValue()[i];

          eventList.add(new HandDisinfectionEvent(eventId, deviceId, employeeId, chemistry, eventTimestamp,
              duration, transactionHash, transactionStatus, eventNr));
        }
        catch (Exception e) {
          LOG.error("Failed to convert database data to HandDisinfectionEvent", e);
        }
      }
      events = eventList.toArray(new HandDisinfectionEvent[eventList.size()]);
    }
    return events;
  }

  private HandDisinfectionEvent loadRemainingInfoForEvent(HandDisinfectionEvent event) {
    SQL.selectInto(DisinfectionSQLs.SELECT_REMAINING, event);
    if (event.getTransactionStatus() == Transaction.PENDING) {
      event = BEANS.get(HandDisinfectionEventTrackerService.class).reloadTransactionStatus(event);
      if (event.getTransactionStatus() != Transaction.PENDING) {
        updateTransactionStatus(event.getEventId(), event.getTransactionStatus());
      }
    }
    return event;
  }

  private void updateTransactionStatus(String eventId, int status) {
    if (Transaction.isValidStatus(status)) {
      SQL.update(DisinfectionSQLs.UPDATE_STATUS,
          new NVPair("transactionStatus", status),
          new NVPair("eventId", eventId));
    }
  }

  private class TrackDisinfectionEventJob implements IRunnable {

    private HandDisinfectionEvent m_event;

    public TrackDisinfectionEventJob(HandDisinfectionEvent event) {
      m_event = event;
    }

    @Override
    public void run() throws Exception {
      m_event = BEANS.get(HandDisinfectionEventTrackerService.class).trackHandDisinfectionEvent(m_event);
      SQL.update(DisinfectionSQLs.UPDATE, m_event);
      if (StringUtility.isNullOrEmpty(m_event.getTransactionHash())) {
        LOG.error("Event could not be tracked. " + m_event.toString());
      }
    }

  }
}
