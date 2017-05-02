package org.eclipse.scout.healthcare.server.disinfection.tracker;

import org.eclipse.scout.rt.platform.config.AbstractStringConfigProperty;

public class HandDisinfectionTrackerProperties {

  public class HandDisinfectionTrackerAddressProperty extends AbstractStringConfigProperty {
    @Override
    public String getKey() {
      return "healthcare.ethereum.handdisinfectiontracker.address";
    }
  }

}
