package org.eclipse.scout.healthcare.shared.person;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

/**
 * <h3>{@link OccupationCodeType}</h3>
 *
 * @author uk
 */
public class OccupationCodeType extends AbstractCodeType<String, String> {

  private static final long serialVersionUID = 1L;
  public static final String ID = "OCCUPATION";

  @Override
  public String getId() {
    return ID;
  }

  @Order(1000)
  public static class DoctorCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;
    public static final String ID = "OCCUPATION.DOCTOR";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Doctor");
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(2000)
  public static class NurseCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;
    public static final String ID = "OCCUPATION.NURSE";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Nurse");
    }

    @Override
    public String getId() {
      return ID;
    }
  }

}
