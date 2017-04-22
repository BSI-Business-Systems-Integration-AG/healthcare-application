package org.eclipse.scout.healthcare.shared.ethereum.smartcontract;

import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.healthcare.client.ethereum.smartcontract.SmartContractAdministrationFormData;
import org.eclipse.scout.healthcare.client.ethereum.smartcontract.SmartContractAdministrationTablePageData;

@ApplicationScoped
@TunnelToServer
public interface ISmartContractAdminstrationService {

  public SmartContractAdministrationTablePageData loadTableData();

  public SmartContractAdministrationFormData load(SmartContractAdministrationFormData formData);

  public SmartContractAdministrationFormData create(SmartContractAdministrationFormData formData);

  public SmartContractAdministrationFormData store(SmartContractAdministrationFormData formData);

  public SmartContractAdministrationFormData store(SmartContractAdministrationFormData formData, boolean overwrite);

  public void delete(SmartContractAdministrationFormData formData);

  public void delete(SmartContractAdministrationFormData[] formDatas);

}
