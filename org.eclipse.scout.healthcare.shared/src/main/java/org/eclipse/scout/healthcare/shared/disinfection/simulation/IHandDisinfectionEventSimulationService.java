package org.eclipse.scout.healthcare.shared.disinfection.simulation;

import org.eclipse.scout.healthcare.shared.disinfection.simulation.HandDisinfectionEventSimulationFormData;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

/**
 * <h3>{@link IHandDisinfectionEventSimulationService}</h3>
 *
 * @author uk
 */
@TunnelToServer
public interface IHandDisinfectionEventSimulationService extends IService {

  /**
   * @param formData
   * @return
   */
  HandDisinfectionEventSimulationFormData prepareSimulate(HandDisinfectionEventSimulationFormData formData);

  /**
   * @param formData
   * @return
   */
  HandDisinfectionEventSimulationFormData simulate(HandDisinfectionEventSimulationFormData formData);
}
