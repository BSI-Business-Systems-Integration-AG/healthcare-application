package org.eclipse.scout.healthcare.client.disinfection.simulation;

import org.eclipse.scout.healthcare.shared.disinfection.simulation.HandDisinfectionEventSimulationFormData;
import org.eclipse.scout.healthcare.shared.disinfection.simulation.IHandDisinfectionEventSimulationService;
import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class HandDisinfectionEventSimulationFormTest {

  @BeanMock
  private IHandDisinfectionEventSimulationService m_mockSvc;

  @Before
  public void setup() {
    HandDisinfectionEventSimulationFormData answer = new HandDisinfectionEventSimulationFormData();
    Mockito.when(m_mockSvc.prepareSimulate(Matchers.any(HandDisinfectionEventSimulationFormData.class))).thenReturn(answer);
    Mockito.when(m_mockSvc.simulate(Matchers.any(HandDisinfectionEventSimulationFormData.class))).thenReturn(answer);
  }

  // TODO [uk] add test cases
}
