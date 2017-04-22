package org.eclipse.scout.healthcare.server.ethereum;

import org.eclipse.scout.rt.platform.config.AbstractStringConfigProperty;

public class EthereumProperties {

  public static class EthereumClientIpProperty extends AbstractStringConfigProperty {

    @Override
    public String getKey() {
      return "healthcare.ethereum.client.ip";
    }

  }

  public static class EthereumClientPortProperty extends AbstractStringConfigProperty {

    @Override
    public String getKey() {
      return "healthcare.ethereum.client.port";
    }

  }

  public static class EthereumClientProperty extends AbstractStringConfigProperty {

    @Override
    public String getKey() {
      return "healthcare.ethereum.client";
    }

    @Override
    protected String getDefaultValue() {
      return "TESTNET";
    }

  }

  public static class EthereumWalletLocation extends AbstractStringConfigProperty {

    @Override
    public String getKey() {
      return "healthcare.ethereum.wallet.location";
    }

  }

  public static class EthereumDefaultAccount extends AbstractStringConfigProperty {

    @Override
    public String getKey() {
      return "healthcare.ethereum.default.account";
    }

  }

}
