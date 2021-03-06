package org.eclipse.scout.healthcare.shared.person;

import javax.annotation.Generated;

import org.eclipse.scout.healthcare.shared.common.AbstractAddressBoxData;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@Generated(value = "org.eclipse.scout.healthcare.client.person.PersonSearchForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class PersonSearchFormData extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public FirstName getFirstName() {
    return getFieldByClass(FirstName.class);
  }

  public LastName getLastName() {
    return getFieldByClass(LastName.class);
  }

  public Location getLocation() {
    return getFieldByClass(Location.class);
  }

  public Occupation getOccupation() {
    return getFieldByClass(Occupation.class);
  }

  public static class FirstName extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;
  }

  public static class LastName extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;
  }

  public static class Location extends AbstractAddressBoxData {

    private static final long serialVersionUID = 1L;
  }

  public static class Occupation extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;
  }
}
