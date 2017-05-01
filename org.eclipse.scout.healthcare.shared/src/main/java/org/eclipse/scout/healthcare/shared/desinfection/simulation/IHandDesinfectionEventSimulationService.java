package org.eclipse.scout.healthcare.shared.desinfection.simulation;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

/**
 * <h3>{@link IHandDesinfectionEventSimulationService}</h3>
 *
 * @author uk
 */
@TunnelToServer
public interface IHandDesinfectionEventSimulationService extends IService {

  /**
   * @param formData
   * @return
   */
  HandDesinfectionEventSimulationFormData prepareSimulate(HandDesinfectionEventSimulationFormData formData);

  /**
   * @param formData
   * @return
   */
  HandDesinfectionEventSimulationFormData simulate(HandDesinfectionEventSimulationFormData formData);
}
