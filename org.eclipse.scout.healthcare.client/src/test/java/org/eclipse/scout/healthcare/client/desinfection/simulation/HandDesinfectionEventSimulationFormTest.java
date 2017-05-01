package org.eclipse.scout.healthcare.client.desinfection.simulation;

import org.eclipse.scout.healthcare.shared.desinfection.simulation.HandDesinfectionEventSimulationFormData;
import org.eclipse.scout.healthcare.shared.desinfection.simulation.IHandDesinfectionEventSimulationService;
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
public class HandDesinfectionEventSimulationFormTest {

  @BeanMock
  private IHandDesinfectionEventSimulationService m_mockSvc;

  @Before
  public void setup() {
    HandDesinfectionEventSimulationFormData answer = new HandDesinfectionEventSimulationFormData();
    Mockito.when(m_mockSvc.prepareSimulate(Matchers.any(HandDesinfectionEventSimulationFormData.class))).thenReturn(answer);
    Mockito.when(m_mockSvc.simulate(Matchers.any(HandDesinfectionEventSimulationFormData.class))).thenReturn(answer);
  }

  // TODO [uk] add test cases
}
