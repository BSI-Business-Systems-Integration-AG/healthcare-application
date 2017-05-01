package org.eclipse.scout.healthcare.server.desinfection.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint128;
import org.web3j.abi.datatypes.generated.Uint256;

/**
 * <h3>{@link HandDesinfectionEventTest}</h3>
 *
 * @author uk
 */
public class HandDesinfectionEventTest {

  private static final Long EVENT_ID = 5l;
  private static final String DEVICE_ID = "e968c586d09244a4a23163edd1ca43b9";
  private static final String EMPLOYEE_ID = "prs01";
  private static final String CHEMISTRY = "Chem 124";
  private static final Date TIMESTAMP = new Date(1493589856L);
  private static final Long DURATION = 9552L;

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.desinfection.model.HandDesinfectionEvent#parse(java.util.List)}.
   */
  @Test
  public void testParse() {
    List<Type> remoteTestEvent = new ArrayList<Type>();
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(EVENT_ID)));
    remoteTestEvent.add(new Utf8String(DEVICE_ID));
    remoteTestEvent.add(new Utf8String(EMPLOYEE_ID));
    remoteTestEvent.add(new Utf8String(CHEMISTRY));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(TIMESTAMP.getTime())));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(DURATION)));

    HandDesinfectionEvent testEvent = HandDesinfectionEvent.parse(remoteTestEvent);
    assertTrue(EVENT_ID.equals(testEvent.getEventId()));
    assertTrue(DEVICE_ID.equals(testEvent.getDeviceId()));
    assertTrue(EMPLOYEE_ID.equals(testEvent.getEmployeeId()));
    assertTrue(CHEMISTRY.equals(testEvent.getChemistry()));
    assertTrue(TIMESTAMP.equals(testEvent.getEventTimestamp()));
    assertTrue(DURATION.equals(testEvent.getDuration()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseNumberOfObjects() {
    List<Type> remoteTestEvent = new ArrayList<Type>();
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(EVENT_ID)));
    remoteTestEvent.add(new Utf8String(DEVICE_ID));
    remoteTestEvent.add(new Utf8String(EMPLOYEE_ID));

    @SuppressWarnings("unused")
    HandDesinfectionEvent testEvent = HandDesinfectionEvent.parse(remoteTestEvent);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseTypeAtIndex0() {
    List<Type> remoteTestEvent = new ArrayList<Type>();
    remoteTestEvent.add(new Uint128(BigInteger.valueOf(EVENT_ID)));
    remoteTestEvent.add(new Utf8String(DEVICE_ID));
    remoteTestEvent.add(new Utf8String(EMPLOYEE_ID));
    remoteTestEvent.add(new Utf8String(CHEMISTRY));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(TIMESTAMP.getTime())));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(DURATION)));

    @SuppressWarnings("unused")
    HandDesinfectionEvent testEvent = HandDesinfectionEvent.parse(remoteTestEvent);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseTypeAtIndex1() {
    List<Type> remoteTestEvent = new ArrayList<Type>();
    remoteTestEvent.add(new Uint128(BigInteger.valueOf(EVENT_ID)));
    remoteTestEvent.add(new Uint256(BigInteger.ONE));
    remoteTestEvent.add(new Utf8String(EMPLOYEE_ID));
    remoteTestEvent.add(new Utf8String(CHEMISTRY));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(TIMESTAMP.getTime())));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(DURATION)));

    @SuppressWarnings("unused")
    HandDesinfectionEvent testEvent = HandDesinfectionEvent.parse(remoteTestEvent);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseTypeAtIndex2() {
    List<Type> remoteTestEvent = new ArrayList<Type>();
    remoteTestEvent.add(new Uint128(BigInteger.valueOf(EVENT_ID)));
    remoteTestEvent.add(new Utf8String(DEVICE_ID));
    remoteTestEvent.add(new Uint256(BigInteger.ONE));
    remoteTestEvent.add(new Utf8String(CHEMISTRY));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(TIMESTAMP.getTime())));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(DURATION)));

    @SuppressWarnings("unused")
    HandDesinfectionEvent testEvent = HandDesinfectionEvent.parse(remoteTestEvent);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseTypeAtIndex3() {
    List<Type> remoteTestEvent = new ArrayList<Type>();
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(EVENT_ID)));
    remoteTestEvent.add(new Utf8String(DEVICE_ID));
    remoteTestEvent.add(new Utf8String(EMPLOYEE_ID));
    remoteTestEvent.add(new Uint256(BigInteger.ONE));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(TIMESTAMP.getTime())));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(DURATION)));

    @SuppressWarnings("unused")
    HandDesinfectionEvent testEvent = HandDesinfectionEvent.parse(remoteTestEvent);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseTypeAtIndex4() {
    List<Type> remoteTestEvent = new ArrayList<Type>();
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(EVENT_ID)));
    remoteTestEvent.add(new Utf8String(DEVICE_ID));
    remoteTestEvent.add(new Utf8String(EMPLOYEE_ID));
    remoteTestEvent.add(new Utf8String(CHEMISTRY));
    remoteTestEvent.add(new Uint128(BigInteger.valueOf(TIMESTAMP.getTime())));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(DURATION)));

    @SuppressWarnings("unused")
    HandDesinfectionEvent testEvent = HandDesinfectionEvent.parse(remoteTestEvent);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseTypeAtIndex5() {
    List<Type> remoteTestEvent = new ArrayList<Type>();
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(EVENT_ID)));
    remoteTestEvent.add(new Utf8String(DEVICE_ID));
    remoteTestEvent.add(new Utf8String(EMPLOYEE_ID));
    remoteTestEvent.add(new Utf8String(CHEMISTRY));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(TIMESTAMP.getTime())));
    remoteTestEvent.add(new Uint128(BigInteger.valueOf(DURATION)));

    @SuppressWarnings("unused")
    HandDesinfectionEvent testEvent = HandDesinfectionEvent.parse(remoteTestEvent);
  }

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.desinfection.model.HandDesinfectionEvent#HandDesinfectionEvent(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.Long)}.
   */
  @Test
  public void testHandDesinfectionEvent() {
    HandDesinfectionEvent testEvent = new HandDesinfectionEvent(DEVICE_ID, EMPLOYEE_ID, CHEMISTRY, TIMESTAMP, DURATION);
    assertNull(testEvent.getEventId());
    assertTrue(DEVICE_ID.equals(testEvent.getDeviceId()));
    assertTrue(EMPLOYEE_ID.equals(testEvent.getEmployeeId()));
    assertTrue(CHEMISTRY.equals(testEvent.getChemistry()));
    assertTrue(TIMESTAMP.equals(testEvent.getEventTimestamp()));
    assertTrue(DURATION.equals(testEvent.getDuration()));
  }

}
