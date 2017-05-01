package org.eclipse.scout.healthcare.server.ethereum;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.scout.rt.platform.util.TriState;
import org.eclipse.scout.rt.platform.util.VerboseUtility;
import org.eclipse.scout.rt.platform.util.date.UTCDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Bytes;
import org.web3j.abi.datatypes.Fixed;
import org.web3j.abi.datatypes.Int;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;

/**
 * WIP: only types we already needed are supported, yet. Other types need to be added.
 */
public final class Web3jConvertUtility {

  private static final Logger LOG = LoggerFactory.getLogger(Web3jConvertUtility.class);

  private static Web3jConvertUtility instance = new Web3jConvertUtility();

  private static final int CHARACTER = 1;
  private static final int BYTE = 2;
  private static final int BOOLEAN = 3;
  private static final int SHORT = 4;
  private static final int INTEGER = 5;
  private static final int LONG = 6;
  private static final int FLOAT = 7;
  private static final int DOUBLE = 8;
  private static final int STRING = 9;
  private static final int OBJECT = 10;
  private static final int BIGINTEGER = 11;
  private static final int BIGDECIMAL = 12;
  private static final int DATE = 13;
  private static final int CALENDAR = 14;
  private static final int SQLDATE = 15;
  private static final int SQLTIME = 16;
  private static final int SQLTIMESTAMP = 17;
  private static final int TRISTATE = 18;
  private static final int UTCDATE = 19;
  private static final int VOID = 9999;

  private static final int ABI_BOOL = 1001;
  private static final int ABI_BYTES = 1002;
  private static final int ABI_NUMERIC_FIXED = 1003;
  private static final int ABI_NUMERIC_INT = 1004;
  private static final int ABI_NUMERIC_UINT = 1005;
  private static final int ABI_ADDRESS = 1006;
  private static final int ABI_UTF8STRING = 1007;

  private final Map<Class, Integer> m_typeMap = new HashMap<Class, Integer>();

  private Web3jConvertUtility() {
    m_typeMap.put(Character.class, Integer.valueOf(CHARACTER));
    m_typeMap.put(Byte.class, Integer.valueOf(BYTE));
    m_typeMap.put(Boolean.class, Integer.valueOf(BOOLEAN));
    m_typeMap.put(TriState.class, Integer.valueOf(TRISTATE));
    m_typeMap.put(Short.class, Integer.valueOf(SHORT));
    m_typeMap.put(Integer.class, Integer.valueOf(INTEGER));
    m_typeMap.put(Long.class, Integer.valueOf(LONG));
    m_typeMap.put(Float.class, Integer.valueOf(FLOAT));
    m_typeMap.put(Double.class, Integer.valueOf(DOUBLE));
    m_typeMap.put(String.class, Integer.valueOf(STRING));
    m_typeMap.put(Object.class, Integer.valueOf(OBJECT));
    m_typeMap.put(BigInteger.class, Integer.valueOf(BIGINTEGER));
    m_typeMap.put(BigDecimal.class, Integer.valueOf(BIGDECIMAL));
    m_typeMap.put(UTCDate.class, Integer.valueOf(UTCDATE));
    m_typeMap.put(Date.class, Integer.valueOf(DATE));
    m_typeMap.put(Calendar.class, Integer.valueOf(CALENDAR));
    m_typeMap.put(GregorianCalendar.class, Integer.valueOf(CALENDAR));
    m_typeMap.put(java.sql.Date.class, Integer.valueOf(SQLDATE));
    m_typeMap.put(Time.class, Integer.valueOf(SQLTIME));
    m_typeMap.put(Timestamp.class, Integer.valueOf(SQLTIMESTAMP));
    m_typeMap.put(Void.class, Integer.valueOf(VOID));
  }

  @SuppressWarnings("unchecked")
  public static <T extends Type> T convertType(Object object, Class<T> clazz) {
    return (T) instance.convertTypeImpl(object, clazz);
  }

  @SuppressWarnings("unchecked")
  public static <T extends Type, S> S convertType(T type, Class<S> clazz) {
    return (S) instance.convertTypeImpl(type, clazz);
  }

  private Object convertTypeImpl(Object o, Class toType) {
    // null check
    if (o == null) {
      return null;
    }
    // direct check
    if (toType.isInstance(o)) {
      return o;
    }

    // need conversion
    Class fromType = o.getClass();
    // array check
    if (toType.isArray()) {
      throw createException(o, fromType, toType, 4, "not implementated");
    }

    if (Type.class.isAssignableFrom(fromType)) {
      return convertBasicValueFromImpl(o, fromType, toType);
    }
    else {
      return convertBasicValueToImpl(o, fromType, toType);
    }
  }

  private Object convertBasicValueFromImpl(Object o, Class fromType, Class toType) {
    // null check
    if (o == null) {
      return null;
    }
    // from type in map
    int fromId = getAbiTypeId(fromType);
    if (fromId == 0) {
      throw createException(o, fromType, toType, 2, "no from-mapping");
    }
    // to type in map
    int toId = getTypeId(toType);
    if (toId == 0) {
      throw createException(o, fromType, toType, 3, "no to-mapping");
    }
    return convertBasicValueImpl(o, fromId, toId, fromType, toType);
  }

  private Object convertBasicValueToImpl(Object o, Class fromType, Class toType) {
    // null check
    if (o == null) {
      return null;
    }
    // from type in map
    int fromId = getTypeId(fromType);
    if (fromId == 0) {
      throw createException(o, fromType, toType, 2, "no from-mapping");
    }
    // to type in map
    int toId = getAbiTypeId(toType);
    if (toId == 0) {
      throw createException(o, fromType, toType, 3, "no to-mapping");
    }
    return convertBasicValueImpl(o, fromId, toId, fromType, toType);
  }

  @SuppressWarnings("unchecked")
  private Object convertBasicValueImpl(Object o, int fromId, int toId, Class fromType, Class toType) {
    // null check
    if (o == null) {
      return null;
    }
    switch (fromId) {
      case ABI_NUMERIC_UINT:
        switch (toId) {
          case LONG:
            return txUintToLong((Uint) o);
          case DATE:
            return txUnitToDate((Uint) o);
        }
        break;
      case ABI_UTF8STRING:
        switch (toId) {
          case STRING:
            return ((Utf8String) o).getValue();
        }
        break;
      case STRING:
        switch (toId) {
          case ABI_UTF8STRING:
            return new Utf8String((String) o);
        }
        break;
      case LONG:
        switch (toId) {
          case ABI_NUMERIC_UINT:
            return txLongToUint((Long) o, toType);
        }
        break;
      case DATE:
        switch (toId) {
          case ABI_NUMERIC_UINT:
            return txDateToUint((Date) o, toType);
        }
        break;
    }

    throw createException(o, fromType, toType, 4, "not implementated");
  }

  private <T extends Uint> Long txUintToLong(T o) {
    BigInteger value = o.getValue();
    return new Long(value.longValue());
  }

  private Object txUnitToDate(Uint o) {
    BigInteger value = o.getValue();
    return new Date(value.longValue());
  }

  @SuppressWarnings("unchecked")
  private <T extends Uint> T txLongToUint(Long o, Class<T> t) {
    T uint = null;
    Constructor<?> cons;
    try {
      cons = t.getConstructor(BigInteger.class);
      uint = (T) cons.newInstance(BigInteger.valueOf(o));
    }
    catch (Exception e) {
      LOG.error(e.getMessage(), e);
      createException(o, Long.class, t, 5, "Could not create an instance");
    }
    return uint;
  }

  @SuppressWarnings("unchecked")
  private <T extends Uint> T txDateToUint(Date o, Class<T> t) {
    T uint = null;
    Constructor<?> cons;
    try {
      cons = t.getConstructor(BigInteger.class);
      Long timestamp = o.getTime();
      uint = (T) cons.newInstance(BigInteger.valueOf(timestamp));
    }
    catch (Exception e) {
      LOG.error(e.getMessage(), e);
      createException(o, Long.class, t, 5, "Could not create an instance");
    }
    return uint;
  }

  /**
   * type to typeId
   */
  private int getTypeId(Class type) {
    Integer id = m_typeMap.get(type);
    if (id == null) {
      return 0;
    }
    else {
      return id.intValue();
    }
  }

  /**
   * type to abiTypeId
   */
  private int getAbiTypeId(Class type) {
    if (Bool.class == type) {
      return ABI_BOOL;
    }
    else if (Bytes.class.isAssignableFrom(type)) {
      return ABI_BYTES;
    }
    else if (Fixed.class.isAssignableFrom(type)) {
      return ABI_NUMERIC_FIXED;
    }
    else if (Int.class.isAssignableFrom(type)) {
      return ABI_NUMERIC_INT;
    }
    else if (Address.class == type) {
      return ABI_ADDRESS;
    }
    else if (Uint.class.isAssignableFrom(type)) {
      return ABI_NUMERIC_UINT;
    }
    else if (Utf8String.class.isAssignableFrom(type)) {
      return ABI_UTF8STRING;
    }
    return 0;
  }

  /**
   * exception builder
   */
  private IllegalArgumentException createException(Object o, Class fromType, Class toType, int code, String msg) {
    return new IllegalArgumentException(
        "converting "
            + VerboseUtility.dumpObject(o)
            + " from "
            + VerboseUtility.dumpType(fromType)
            + " to "
            + VerboseUtility.dumpType(toType)
            + " failed with code "
            + code
            + " (" + msg + ")");
  }

}
