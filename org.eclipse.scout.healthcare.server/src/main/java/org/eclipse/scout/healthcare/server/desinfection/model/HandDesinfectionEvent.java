package org.eclipse.scout.healthcare.server.desinfection.model;

import java.util.Date;
import java.util.List;

import org.eclipse.scout.healthcare.server.ethereum.Web3jConvertUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

public class HandDesinfectionEvent {

  private Long m_eventId;
  private String m_deviceId;
  private String m_employeeId;
  private String m_chemistry;
  private Date m_eventTimestamp;
  private Long m_duration;

  public static HandDesinfectionEvent parse(List<Type> typeList) {
    HandDesinfectionEvent event = new HandDesinfectionEvent();
    event.parseInternal(typeList);
    return event;
  }

  private HandDesinfectionEvent() {
  }

  public HandDesinfectionEvent(String deviceId, String employeeId, String chemistry, Date eventTimestamp, Long duration) {
    if (StringUtility.isNullOrEmpty(deviceId) || StringUtility.isNullOrEmpty(employeeId) || StringUtility.isNullOrEmpty(chemistry)
        || null == eventTimestamp || null == duration) {
      throw new IllegalArgumentException("All parameters must be set.");
    }
    if (duration <= 0) {
      throw new IllegalArgumentException("Duration must be bigger than zero.");
    }

    setDeviceId(deviceId);
    setEmployeeId(employeeId);
    setChemistry(chemistry);
    setEventTimestamp(eventTimestamp);
    setDuration(duration);
  }

  public Long getEventId() {
    return m_eventId;
  }

  public Uint256 getEventIdTyped() {
    return Web3jConvertUtility.convertType(m_eventId, Uint256.class);
  }

  private void setEventId(Long eventId) {
    this.m_eventId = eventId;
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

  private void parseInternal(List<Type> typeList) {
    if (null == typeList || typeList.size() != 6) {
      throw new IllegalArgumentException("Type list has not the expected number of elements (6 elements expected).");
    }

    if (!Uint256.class.isInstance(typeList.get(0))) {
      throw new IllegalArgumentException("Expected element at index 0 to be of type Uint246. Given type is " + typeList.get(0).getClass().getSimpleName());
    }
    setEventId(Web3jConvertUtility.convertType(typeList.get(0), Long.class));

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
      throw new IllegalArgumentException("Expected element at index 0 to be of type Uint246. Given type is " + typeList.get(5).getClass().getSimpleName());
    }
    setDuration(Web3jConvertUtility.convertType(typeList.get(5), Long.class));
  }
}
