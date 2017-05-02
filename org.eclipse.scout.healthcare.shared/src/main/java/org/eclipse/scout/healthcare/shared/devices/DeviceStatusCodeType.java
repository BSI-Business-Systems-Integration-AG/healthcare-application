package org.eclipse.scout.healthcare.shared.devices;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

public class DeviceStatusCodeType extends AbstractCodeType<String, String> {

  private static final long serialVersionUID = 1L;
  public static final String ID = "DEVICESTATUS";

  @Override
  public String getId() {
    return ID;
  }

  @Order(1000)
  public static class ReadyCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;
    public static final String ID = "DEVICESTATUS.READY";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Ready");
    }

    @Override
    protected String getConfiguredForegroundColor() {
      return "0DAF66";
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(2000)
  public static class OfflineCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;
    public static final String ID = "DEVICESTATUS.OFFLINE";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Offline");
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(3000)
  public static class RefillCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;
    public static final String ID = "DEVICESTATUS.REFILL";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Refill");
    }

    @Override
    protected String getConfiguredForegroundColor() {
      return "FF6060";
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(4000)
  public static class RefillNecessaryCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;
    public static final String ID = "DEVICESTATUS.REFILLNECESSARY";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("RefillNecessary");
    }

    @Override
    protected String getConfiguredForegroundColor() {
      return "FDAD4F";
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(5000)
  public static class DesinfectsCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;
    public static final String ID = "DEVICESTATUS.DESINFECTS";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Desinfects");
    }

    @Override
    protected String getConfiguredForegroundColor() {
      return "2B73B3";
    }

    @Override
    public String getId() {
      return ID;
    }
  }

}
