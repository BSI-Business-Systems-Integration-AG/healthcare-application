package org.eclipse.scout.healthcare.solidity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.eclipse.scout.healthcare.solidity.tool.Web3jHelper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.utils.Convert.Unit;


/**
 * Unit test for simple App.
 */
public class TestHandDisinfectionTracker 
{

	private static final BigInteger GAS_PRICE_DEFAULT = BigInteger.valueOf(20_000_000_000L);
	private static final BigInteger GAS_LIMIT_CONTRACT_DEPLOY = BigInteger.valueOf(4_700_000L);

	private static final Uint256 EVENT_ID = new Uint256(BigInteger.ONE);
	private static final Utf8String DEVICE_ID = new Utf8String("e968c586d09244a4a23163edd1ca43b9");
	private static final Utf8String EMPLOYEE_ID = new Utf8String("prs01");
	private static final Utf8String CHEMISTRY = new Utf8String("Chem 124");
	private static final Uint256 TIMESTAMP = new Uint256(BigInteger.valueOf(1493589856L));
	private static final Uint256 DURATION = new Uint256(BigInteger.valueOf(9552L));
	private static final Utf8String UUID = new Utf8String("019a270e-4885-415d-aafc-f6f879eb6683");
	
	@BeforeClass
	public static void setUp() {
		try {
			String ownerAddress = Alice.ADDRESS;
			String coinbaseAddress = Web3jHelper.getAccount(0);
			BigDecimal coinbaseBalance = Web3jHelper.getBalance(coinbaseAddress, Unit.ETHER);
			BigDecimal ownerBalance = Web3jHelper.getBalance(ownerAddress, Unit.ETHER);
			System.out.println(String.format("Coinbase balance [Ether] %s with address %s", coinbaseBalance, coinbaseAddress));
			System.out.println(String.format("Contact owner balence [Ether] %s with address %s", ownerBalance, ownerAddress));

			if (ownerBalance.compareTo(BigDecimal.TEN) < 0) {
				String txHash = Web3jHelper.transfer(coinbaseAddress, ownerAddress, 10, Unit.ETHER);
				System.out.println(String.format("10 ether to owner sent, txHash=%s", txHash));
			}
		} catch (Exception e) {
			throw new RuntimeException("Could not set up unit test", e);
		}
	}

	@Test
	public void testConnection() {
		Assert.assertNotNull("Web3j is null", Web3jHelper.getWeb3j());
		Assert.assertNotEquals("Unexpected ethereum client version: ", "<undefined>", Web3jHelper.getClientVersion());
	}


	@Test
    public void testDeploy()
    {
    	HandDisinfectionTracker contract = null;
		try {
			contract = deployContract();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Exception during unit test", e);
		} finally {
			killContract(contract);
		}
    }
	
	@Test
	public void testTrackEvent() {
	    HandDisinfectionTracker contract = null;
	    try {
			contract = deployContract();
			contract.trackHandDisinfectionEvent(
					DEVICE_ID, 
					EMPLOYEE_ID, 
					CHEMISTRY, 
					TIMESTAMP, 
					DURATION,
					UUID).get();
			
			List<Type> event = contract.disinfectionEvents(new Uint256(BigInteger.ZERO)).get();
			Assert.assertTrue(event != null);
			Assert.assertTrue(event.size() == 7);
			Assert.assertTrue(event.get(0) instanceof Uint256 && EVENT_ID.equals(event.get(0)));
			Assert.assertTrue(event.get(1) instanceof Utf8String && DEVICE_ID.equals(event.get(1)));
			Assert.assertTrue(event.get(2) instanceof Utf8String && EMPLOYEE_ID.equals(event.get(2)));
			Assert.assertTrue(event.get(3) instanceof Utf8String && CHEMISTRY.equals(event.get(3)));
			Assert.assertTrue(event.get(4) instanceof Uint256 && TIMESTAMP.equals(event.get(4)));
			Assert.assertTrue(event.get(5) instanceof Uint256 && DURATION.equals(event.get(5)));
			Assert.assertTrue(event.get(6) instanceof Utf8String && UUID.equals(event.get(6)));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception during unit test");
		} finally {
			killContract(contract);
		}
	}
    
    private HandDisinfectionTracker deployContract() throws InterruptedException, ExecutionException {
		Future<HandDisinfectionTracker> contractFuture = HandDisinfectionTracker.deploy(Web3jHelper.getWeb3j(), Alice.CREDENTIALS, GAS_PRICE_DEFAULT, GAS_LIMIT_CONTRACT_DEPLOY, BigInteger.ZERO);

		HandDisinfectionTracker contract = contractFuture.get();

		System.out.println(String.format("Contract deployed at addess %s", contract.getContractAddress()));
		Assert.assertFalse(null == contract);
		Assert.assertFalse(null == contract.getContractAddress());
		Assert.assertFalse("".equals(contract.getContractAddress()));
		
		return contract;
	}
    
    private void killContract(HandDisinfectionTracker contract) {
    	if (null != contract) {
    		try {
    			contract.kill().get();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
	}
}
