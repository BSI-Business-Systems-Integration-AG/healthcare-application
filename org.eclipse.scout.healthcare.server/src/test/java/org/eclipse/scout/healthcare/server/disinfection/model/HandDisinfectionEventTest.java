package org.eclipse.scout.healthcare.server.disinfection.model;

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
 * <h3>{@link HandDisinfectionEventTest}</h3>
 *
 * @author uk
 */
public class HandDisinfectionEventTest {

  private static final Long EVENT_ID = 5l;
  private static final String DEVICE_ID = "e968c586d09244a4a23163edd1ca43b9";
  private static final String EMPLOYEE_ID = "prs01";
  private static final String CHEMISTRY = "Chem 124";
  private static final Date TIMESTAMP = new Date(1493589856L);
  private static final Long DURATION = 9552L;
  private static final String UUID = "019a270e-4885-415d-aafc-f6f879eb6683";

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.disinfection.model.HandDisinfectionEvent#parse(java.util.List)}.
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
    remoteTestEvent.add(new Utf8String(UUID));

    HandDisinfectionEvent testEvent = HandDisinfectionEvent.parse(remoteTestEvent);
    assertTrue(EVENT_ID.equals(testEvent.getEventNr()));
    assertTrue(DEVICE_ID.equals(testEvent.getDeviceId()));
    assertTrue(EMPLOYEE_ID.equals(testEvent.getEmployeeId()));
    assertTrue(CHEMISTRY.equals(testEvent.getChemistry()));
    assertTrue(TIMESTAMP.equals(testEvent.getEventTimestamp()));
    assertTrue(DURATION.equals(testEvent.getDuration()));
    assertTrue(UUID.equals(testEvent.getEventId()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseNumberOfObjects() {
    List<Type> remoteTestEvent = new ArrayList<Type>();
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(EVENT_ID)));
    remoteTestEvent.add(new Utf8String(DEVICE_ID));
    remoteTestEvent.add(new Utf8String(EMPLOYEE_ID));

    @SuppressWarnings("unused")
    HandDisinfectionEvent testEvent = HandDisinfectionEvent.parse(remoteTestEvent);
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
    remoteTestEvent.add(new Utf8String(UUID));

    @SuppressWarnings("unused")
    HandDisinfectionEvent testEvent = HandDisinfectionEvent.parse(remoteTestEvent);
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
    remoteTestEvent.add(new Utf8String(UUID));

    @SuppressWarnings("unused")
    HandDisinfectionEvent testEvent = HandDisinfectionEvent.parse(remoteTestEvent);
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
    remoteTestEvent.add(new Utf8String(UUID));

    @SuppressWarnings("unused")
    HandDisinfectionEvent testEvent = HandDisinfectionEvent.parse(remoteTestEvent);
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
    remoteTestEvent.add(new Utf8String(UUID));

    @SuppressWarnings("unused")
    HandDisinfectionEvent testEvent = HandDisinfectionEvent.parse(remoteTestEvent);
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
    remoteTestEvent.add(new Utf8String(UUID));

    @SuppressWarnings("unused")
    HandDisinfectionEvent testEvent = HandDisinfectionEvent.parse(remoteTestEvent);
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
    remoteTestEvent.add(new Utf8String(UUID));

    @SuppressWarnings("unused")
    HandDisinfectionEvent testEvent = HandDisinfectionEvent.parse(remoteTestEvent);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseTypeAtIndex6() {
    List<Type> remoteTestEvent = new ArrayList<Type>();
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(EVENT_ID)));
    remoteTestEvent.add(new Utf8String(DEVICE_ID));
    remoteTestEvent.add(new Utf8String(EMPLOYEE_ID));
    remoteTestEvent.add(new Utf8String(CHEMISTRY));
    remoteTestEvent.add(new Uint256(BigInteger.valueOf(TIMESTAMP.getTime())));
    remoteTestEvent.add(new Uint128(BigInteger.valueOf(DURATION)));
    remoteTestEvent.add(new Uint256(BigInteger.ONE));

    @SuppressWarnings("unused")
    HandDisinfectionEvent testEvent = HandDisinfectionEvent.parse(remoteTestEvent);
  }

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.disinfection.model.HandDisinfectionEvent#HandDisinfectionEvent(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.Long)}.
   */
  @Test
  public void testHandDisinfectionEvent() {
    HandDisinfectionEvent testEvent = new HandDisinfectionEvent(DEVICE_ID, EMPLOYEE_ID, CHEMISTRY, TIMESTAMP, DURATION);
    assertNull(testEvent.getEventNr());
    assertTrue(DEVICE_ID.equals(testEvent.getDeviceId()));
    assertTrue(EMPLOYEE_ID.equals(testEvent.getEmployeeId()));
    assertTrue(CHEMISTRY.equals(testEvent.getChemistry()));
    assertTrue(TIMESTAMP.equals(testEvent.getEventTimestamp()));
    assertTrue(DURATION.equals(testEvent.getDuration()));
    assertTrue(null != testEvent.getEventId() && !"".equals(testEvent.getEventId()));
  }

}
