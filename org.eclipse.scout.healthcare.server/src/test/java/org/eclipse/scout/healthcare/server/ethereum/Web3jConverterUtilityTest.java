package org.eclipse.scout.healthcare.server.ethereum;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Date;

import org.junit.Test;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

public class Web3jConverterUtilityTest {

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.ethereum.Web3jConvertUtility#convertType(java.lang.Object, java.lang.Class)}.
   */
  @Test
  public void testConvertTypeLongToUint256() {
    Long value = 125L;
    Object convertedObject = Web3jConvertUtility.convertType(value, Uint256.class);
    assertTrue(convertedObject instanceof Uint256);
    Uint256 convertedValue = (Uint256) convertedObject;
    Uint256 testValue = new Uint256(BigInteger.valueOf(125L));
    assertTrue(testValue.equals(convertedValue));
  }

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.ethereum.Web3jConvertUtility#convertType(java.lang.Object, java.lang.Class)}.
   */
  @Test
  public void testConvertTypeIntegerToUint256() {
    Integer value = 125;
    Object convertedObject = Web3jConvertUtility.convertType(value, Uint256.class);
    assertTrue(convertedObject instanceof Uint256);
    Uint256 convertedValue = (Uint256) convertedObject;
    Uint256 testValue = new Uint256(BigInteger.valueOf(value.longValue()));
    assertTrue(testValue.equals(convertedValue));
  }

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.ethereum.Web3jConvertUtility#convertType(java.lang.Object, java.lang.Class)}.
   */
  @Test
  public void testConvertTypeIntToUint256() {
    int value = 125;
    Object convertedObject = Web3jConvertUtility.convertType(value, Uint256.class);
    assertTrue(convertedObject instanceof Uint256);
    Uint256 convertedValue = (Uint256) convertedObject;
    Uint256 testValue = new Uint256(BigInteger.valueOf(Integer.valueOf(value).longValue()));
    assertTrue(testValue.equals(convertedValue));
  }

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.ethereum.Web3jConvertUtility#convertType(java.lang.Object, java.lang.Class)}.
   */
  @Test
  public void testConvertTypeDateToUint256() {
    Long timestamp = System.currentTimeMillis() / 1000L;
    Date value = new Date(timestamp);
    Object convertedObject = Web3jConvertUtility.convertType(value, Uint256.class);
    assertTrue(convertedObject instanceof Uint256);
    Uint256 convertedValue = (Uint256) convertedObject;
    Uint256 testValue = new Uint256(BigInteger.valueOf(timestamp));
    assertTrue(testValue.equals(convertedValue));
  }

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.ethereum.Web3jConvertUtility#convertType(java.lang.Object, java.lang.Class)}.
   */
  @Test
  public void testConvertTypeStringToUtf8String() {
    String value = "Testing Web3jConverterUtility. Converting from String to Utf8String";
    Object convertedObject = Web3jConvertUtility.convertType(value, Utf8String.class);
    assertTrue(convertedObject instanceof Utf8String);
    Utf8String convertedValue = (Utf8String) convertedObject;
    Utf8String testValue = new Utf8String(value);
    assertTrue(testValue.equals(convertedValue));
  }

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.ethereum.Web3jConvertUtility#convertType(org.web3j.abi.datatypes.Type, java.lang.Class)}.
   */
  @Test
  public void testConvertUint256ToLong() {
    Uint256 value = new Uint256(BigInteger.valueOf(125L));
    Object convertedObject = Web3jConvertUtility.convertType(value, Long.class);
    assertTrue(convertedObject instanceof Long);
    Long convertedValue = (Long) convertedObject;
    Long testValue = 125L;
    assertTrue(testValue.equals(convertedValue));
  }

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.ethereum.Web3jConvertUtility#convertType(org.web3j.abi.datatypes.Type, java.lang.Class)}.
   */
  @Test
  public void testConvertUint256ToDate() {
    Long timestamp = System.currentTimeMillis() / 1000L;
    Uint256 value = new Uint256(BigInteger.valueOf(timestamp));
    Object convertedObject = Web3jConvertUtility.convertType(value, Date.class);
    assertTrue(convertedObject instanceof Date);
    Date convertedValue = (Date) convertedObject;
    Date testValue = new Date(timestamp);
    assertTrue(testValue.equals(convertedValue));
  }

  /**
   * Test method for
   * {@link org.eclipse.scout.healthcare.server.ethereum.Web3jConvertUtility#convertType(org.web3j.abi.datatypes.Type, java.lang.Class)}.
   */
  @Test
  public void testConvertUtf8StringToString() {
    String testValue = "Testing Web3jConverterUtility. Converting from String to Utf8String";
    Utf8String value = new Utf8String(testValue);
    Object convertedObject = Web3jConvertUtility.convertType(value, String.class);
    assertTrue(convertedObject instanceof String);
    String convertedValue = (String) convertedObject;
    assertTrue(testValue.equals(convertedValue));
  }

}
