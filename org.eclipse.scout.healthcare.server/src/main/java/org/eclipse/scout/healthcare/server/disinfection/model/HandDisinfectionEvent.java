package org.eclipse.scout.healthcare.server.disinfection.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.eclipse.scout.healthcare.server.ethereum.Web3jConvertUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

public class HandDisinfectionEvent {

  private Long m_eventNr;
  private String m_eventId;
  private String m_deviceId;
  private String m_employeeId;
  private String m_chemistry;
  private Date m_eventTimestamp;
  private Long m_duration;
  private String m_transactionHash;
  private int m_transactionStatus;

  public static HandDisinfectionEvent parse(List<Type> typeList) {
    HandDisinfectionEvent event = new HandDisinfectionEvent();
    event.parseInternal(typeList);
    return event;
  }

  private HandDisinfectionEvent() {
  }

  public HandDisinfectionEvent(String deviceId, String employeeId, String chemistry, Date eventTimestamp, Long duration) {
    if (StringUtility.isNullOrEmpty(deviceId) || StringUtility.isNullOrEmpty(employeeId) || StringUtility.isNullOrEmpty(chemistry)
        || null == eventTimestamp || null == duration) {
      throw new IllegalArgumentException("All parameters must be set.");
    }
    if (duration <= 0) {
      throw new IllegalArgumentException("Duration must be bigger than zero.");
    }

    setEventId(UUID.randomUUID().toString());
    setDeviceId(deviceId);
    setEmployeeId(employeeId);
    setChemistry(chemistry);
    setEventTimestamp(eventTimestamp);
    setDuration(duration);
  }

  public Long getEventNr() {
    return m_eventNr;
  }

  public Uint256 getEventNrTyped() {
    return Web3jConvertUtility.convertType(m_eventId, Uint256.class);
  }

  private void setEventNr(Long eventNr) {
    this.m_eventNr = eventNr;
  }

  public String getDeviceId() {
    return m_deviceId;
  }

  public Utf8String getDeviceIdTyped() {
    return Web3jConvertUtility.convertType(m_deviceId, Utf8String.class);
  }

  private void setDeviceId(String deviceId) {
    this.m_deviceId = deviceId;
  }

  public String getEmployeeId() {
    return m_employeeId;
  }

  public Utf8String getEmplyoeeIdTyped() {
    return Web3jConvertUtility.convertType(m_employeeId, Utf8String.class);
  }

  private void setEmployeeId(String employeeId) {
    this.m_employeeId = employeeId;
  }

  public String getChemistry() {
    return m_chemistry;
  }

  public Utf8String getChemistryTyped() {
    return Web3jConvertUtility.convertType(m_chemistry, Utf8String.class);
  }

  private void setChemistry(String chemistry) {
    this.m_chemistry = chemistry;
  }

  public Date getEventTimestamp() {
    return m_eventTimestamp;
  }

  public Uint256 getEventTimestampTyped() {
    return Web3jConvertUtility.convertType(m_eventTimestamp, Uint256.class);
  }

  private void setEventTimestamp(Date eventTimestamp) {
    this.m_eventTimestamp = eventTimestamp;
  }

  public Long getDuration() {
    return m_duration;
  }

  public Uint256 getDurationTyped() {
    return Web3jConvertUtility.convertType(m_duration, Uint256.class);
  }

  private void setDuration(Long duration) {
    this.m_duration = duration;
  }

  public String getTransactionHash() {
    return m_transactionHash;
  }

  public void setTransactionHash(String transactionHash) {
    m_transactionHash = transactionHash;
  }

  public int getTransactionStatus() {
    return m_transactionStatus;
  }

  public void setTransactionStatus(int transactionStatus) {
    m_transactionStatus = transactionStatus;
  }

  public String getEventId() {
    return m_eventId;
  }

  public Utf8String getEventIdTyped() {
    return Web3jConvertUtility.convertType(m_eventId, Utf8String.class);
  }

  public void setEventId(String eventId) {
    m_eventId = eventId;
  }

  private void parseInternal(List<Type> typeList) {
    if (null == typeList || typeList.size() != 7) {
      throw new IllegalArgumentException("Type list has not the expected number of elements (6 elements expected).");
    }

    if (!Uint256.class.isInstance(typeList.get(0))) {
      throw new IllegalArgumentException("Expected element at index 0 to be of type Uint246. Given type is " + typeList.get(0).getClass().getSimpleName());
    }
    setEventNr(Web3jConvertUtility.convertType(typeList.get(0), Long.class));

    if (!Utf8String.class.isInstance(typeList.get(1))) {
      throw new IllegalArgumentException("Expected element at index 1 to be of type Utf8String. Given type is " + typeList.get(1).getClass().getSimpleName());
    }
    setDeviceId(Web3jConvertUtility.convertType(typeList.get(1), String.class));

    if (!Utf8String.class.isInstance(typeList.get(2))) {
      throw new IllegalArgumentException("Expected element at index 2 to be of type Utf8String. Given type is " + typeList.get(2).getClass().getSimpleName());
    }
    setEmployeeId(Web3jConvertUtility.convertType(typeList.get(2), String.class));

    if (!Utf8String.class.isInstance(typeList.get(3))) {
      throw new IllegalArgumentException("Expected element at index 3 to be of type Utf8String. Given type is " + typeList.get(3).getClass().getSimpleName());
    }
    setChemistry(Web3jConvertUtility.convertType(typeList.get(3), String.class));

    if (!Uint256.class.isInstance(typeList.get(4))) {
      throw new IllegalArgumentException("Expected element at index 4 to be of type Uint246. Given type is " + typeList.get(4).getClass().getSimpleName());
    }
    setEventTimestamp(Web3jConvertUtility.convertType(typeList.get(4), Date.class));

    if (!Uint256.class.isInstance(typeList.get(5))) {
      throw new IllegalArgumentException("Expected element at index 5 to be of type Uint246. Given type is " + typeList.get(5).getClass().getSimpleName());
    }
    setDuration(Web3jConvertUtility.convertType(typeList.get(5), Long.class));

    if (!Utf8String.class.isInstance(typeList.get(6))) {
      throw new IllegalArgumentException("Expected element at index 6 to be of type Utf8String. Given type is " + typeList.get(6).getClass().getSimpleName());
    }
    setEventId(Web3jConvertUtility.convertType(typeList.get(6), String.class));
  }
}
