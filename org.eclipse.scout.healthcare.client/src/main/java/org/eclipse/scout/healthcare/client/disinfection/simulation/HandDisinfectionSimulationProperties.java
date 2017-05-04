package org.eclipse.scout.healthcare.client.disinfection.simulation;

import org.eclipse.scout.rt.platform.config.AbstractBooleanConfigProperty;

public class HandDisinfectionSimulationProperties {

  public static class HandDisinfectionSimulationShowFormProperty extends AbstractBooleanConfigProperty {

    @Override
    public String getKey() {
      return "healthcare.disinfection.simulation.show.form";
    }

    @Override
    protected Boolean getDefaultValue() {
      return false;
    }

  }

}
