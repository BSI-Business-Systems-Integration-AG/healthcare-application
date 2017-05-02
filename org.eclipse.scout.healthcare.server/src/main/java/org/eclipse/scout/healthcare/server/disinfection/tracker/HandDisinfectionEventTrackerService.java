package org.eclipse.scout.healthcare.server.disinfection.tracker;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.eclipse.scout.healthcare.server.disinfection.HandDisinfectionTracker;
import org.eclipse.scout.healthcare.server.disinfection.model.HandDisinfectionEvent;
import org.eclipse.scout.healthcare.server.disinfection.tracker.HandDisinfectionTrackerProperties.HandDisinfectionTrackerAddressProperty;
import org.eclipse.scout.healthcare.server.ethereum.EthereumProperties.EthereumDefaultAccountPasswordProperty;
import org.eclipse.scout.healthcare.server.ethereum.EthereumProperties.EthereumDefaultAccountProperty;
import org.eclipse.scout.healthcare.server.ethereum.EthereumService;
import org.eclipse.scout.healthcare.server.ethereum.Web3jConvertUtility;
import org.eclipse.scout.healthcare.server.ethereum.model.Account;
import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@ApplicationScoped
public class HandDisinfectionEventTrackerService {

  private static final Logger LOG = LoggerFactory.getLogger(HandDisinfectionEventTrackerService.class);

  public static final BigInteger GAS_PRICE_DEFAULT = BigInteger.valueOf(20_000_000_000L);
  public static final BigInteger GAS_LIMIT_DEFAULT = BigInteger.valueOf(40_000L);
  public static final BigInteger GAS_LIMIT_DEFAULT_LOAD = BigInteger.valueOf(4_000_000L);
  public static final BigInteger GAS_LIMIT_DEFAULT_DEPLOY = BigInteger.valueOf(4_000_000L);

  private HandDisinfectionTracker m_contract;

  /**
   * Deploys the hand disinfection tracker contract. The method blocks until the contract is deployed.
   *
   * @return address of the deployed contract
   * @throws ExecutionException
   * @throws InterruptedException
   */
  public String deploy(Credentials credentials, BigInteger gasPrice, BigInteger gasLimit)
      throws InterruptedException, ExecutionException {

    HandDisinfectionTracker contract = HandDisinfectionTracker
        .deploy(getWeb3j(), credentials, gasPrice, gasLimit, BigInteger.ZERO)
        .get();

    LOG.info("contract successfully deployed at address" + contract.getContractAddress());

    m_contract = contract;

    return contract.getContractAddress();
  }

  public HandDisinfectionEvent trackHandDisinfectionEvent(HandDisinfectionEvent event) {
    if (null == event) {
      throw new IllegalArgumentException("event is not allowed to be null.");
    }
    try {
      TransactionReceipt receipt = getContract()
          .trackHandDisinfectionEvent(
              event.getDeviceIdTyped(), event.getEmplyoeeIdTyped(), event.getChemistryTyped(), event.getEventTimestampTyped(), event.getDurationTyped())
          .get();
      event.setTransactionHash(receipt.getTransactionHash());
    }
    catch (InterruptedException | ExecutionException e) {
      throw new ProcessingException("Could not track event in contract.", e);
    }

    return event;
  }

  public HandDisinfectionEvent[] getAllHandDisinfectionEvents() {
    boolean reachedLastEvent = false;
    int index = 0;
    List<HandDisinfectionEvent> events = new ArrayList<HandDisinfectionEvent>();
    while (!reachedLastEvent) {
      HandDisinfectionEvent event = getHandDisinfectionEventAtIndex(index);
      if (null != event) {
        events.add(event);
      }
      else {
        reachedLastEvent = true;
      }

      index = index + 1;
    }

    return events.toArray(new HandDisinfectionEvent[events.size()]);
  }

  private HandDisinfectionEvent getHandDisinfectionEventAtIndex(int index) {
    List<Type> list = null;
    try {
      list = getContract().DisinfectionEvents(Web3jConvertUtility.convertType(index, Uint256.class)).get();
    }
    catch (InterruptedException | ExecutionException e) {
      // nop. Reached last event.
    }

    HandDisinfectionEvent event = null;
    if (null != list) {
      try {
        event = HandDisinfectionEvent.parse(list);
      }
      catch (IllegalArgumentException e) {
        LOG.error("Contract did not return expected list of values. Check contract or event implementation.", e);
      }
    }

    return event;
  }

  private Web3j getWeb3j() {
    return BEANS.get(EthereumService.class).getWeb3j();
  }

  private HandDisinfectionTracker getContract() throws InterruptedException, ExecutionException {
    if (null == m_contract) {
      String accountAddress = CONFIG.getPropertyValue(EthereumDefaultAccountProperty.class);
      String accountPassword = CONFIG.getPropertyValue(EthereumDefaultAccountPasswordProperty.class);
      if (StringUtility.hasText(accountAddress) && StringUtility.hasText(accountPassword)) {
        Account account = BEANS.get(EthereumService.class).getWallet(accountAddress, accountPassword);
        if (null != account) {
          String contractAddress = CONFIG.getPropertyValue(HandDisinfectionTrackerAddressProperty.class);
          if (StringUtility.hasText(contractAddress)) {
            m_contract = load(contractAddress, account.getCredentials(), GAS_PRICE_DEFAULT, GAS_LIMIT_DEFAULT_LOAD);
          }
          if (BEANS.get(EthereumService.class).isUseTestrpc() && null == m_contract) {
            accountAddress = deploy(account.getCredentials(), GAS_PRICE_DEFAULT, GAS_LIMIT_DEFAULT_DEPLOY);
          }
        }
      }
      if (null == m_contract) {
        throw new ProcessingException("could not load contract");
      }
    }
    return m_contract;
  }

  private HandDisinfectionTracker load(String contractAddress, Credentials credentials, BigInteger gasPriceDefault, BigInteger gasLimitDefaultLoad) {
    HandDisinfectionTracker contract = HandDisinfectionTracker
        .load(contractAddress, getWeb3j(), credentials, gasPriceDefault, gasLimitDefaultLoad);
    return contract;
  }
}
