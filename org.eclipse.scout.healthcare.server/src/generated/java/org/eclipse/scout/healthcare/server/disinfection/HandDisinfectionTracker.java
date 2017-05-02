package org.eclipse.scout.healthcare.server.disinfection;

import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.0.2.
 */
public final class HandDisinfectionTracker extends Contract {
    private static final String BINARY = "0x6060604052341561000c57fe5b5b60008054600160a060020a03191633600160a060020a0316179055600180555b5b6106388061003d6000396000f300606060405263ffffffff60e060020a60003504166341c0e1b5811461003757806395d26d1114610049578063e4dcef9d14610122575bfe5b341561003f57fe5b6100476102ca565b005b341561005157fe5b610047600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375050604080516020601f89358b0180359182018390048302840183019094528083529799988101979196509182019450925082915084018382808284375050604080516020601f89358b01803591820183900483028401830190945280835297999881019791965091820194509250829150840183828082843750949650508435946020013593506102f292505050565b005b341561012a57fe5b6101356004356103db565b604080518781526080810184905260a0810183905260c0602082018181528854600260001961010060018416150201909116049183018290529192830190606084019060e08501908a9080156101cc5780601f106101a1576101008083540402835291602001916101cc565b820191906000526020600020905b8154815290600101906020018083116101af57829003601f168201915b50508481038352885460026000196101006001841615020190911604808252602090910190899080156102405780601f1061021557610100808354040283529160200191610240565b820191906000526020600020905b81548152906001019060200180831161022357829003601f168201915b50508481038252875460026000196101006001841615020190911604808252602090910190889080156102b45780601f10610289576101008083540402835291602001916102b4565b820191906000526020600020905b81548152906001019060200180831161029757829003601f168201915b5050995050505050505050505060405180910390f35b60005433600160a060020a03908116911614156102ef57600054600160a060020a0316ff5b5b565b6102fa61043d565b6103033361041e565b506040805160c081018252600180548082018255825260208201889052918101869052606081018590526080810184905260a0810183905260028054919290919081016103508382610486565b916000526020600020906006020160005b508251815560208084015180518593926103829260018501929101906104b8565b506040820151805161039e9160028401916020909101906104b8565b50606082015180516103ba9160038401916020909101906104b8565b506080820151816004015560a082015181600501555050505b505050505050565b60028054829081106103e957fe5b906000526020600020906006020160005b50805460048201546005830154919350600183019260028101926003909101919086565b600054600160a060020a038281169116146104395760006000fd5b5b50565b60c06040519081016040528060008152602001610458610537565b8152602001610465610537565b8152602001610472610537565b815260200160008152602001600081525090565b8154818355818115116104b2576006028160060283600052602060002091820191016104b29190610549565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106104f957805160ff1916838001178555610526565b82800160010185558215610526579182015b8281111561052657825182559160200191906001019061050b565b5b506105339291506105a3565b5090565b60408051602081019091526000815290565b6105a091905b8082111561053357600080825561056960018301826105c4565b6105776002830160006105c4565b6105856003830160006105c4565b50600060048201819055600582015560060161054f565b5090565b90565b6105a091905b8082111561053357600081556001016105a9565b5090565b90565b50805460018160011615610100020316600290046000825580601f106105ea5750610439565b601f01602090049060005260206000209081019061043991906105a3565b5b505600a165627a7a72305820664c83d5f0ea04780775ac374d13521b9b90809cdffd5a9af87f415ea21015d40029";

    private HandDisinfectionTracker(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private HandDisinfectionTracker(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> trackHandDisinfectionEvent(Utf8String _deviceId, Utf8String _employeeId, Utf8String _chemistry, Uint256 _timestamp, Uint256 _duration) {
        Function function = new Function("trackHandDisinfectionEvent", Arrays.<Type>asList(_deviceId, _employeeId, _chemistry, _timestamp, _duration), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<List<Type>> DisinfectionEvents(Uint256 param0) {
        Function function = new Function("DisinfectionEvents", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public static Future<HandDisinfectionTracker> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(HandDisinfectionTracker.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Future<HandDisinfectionTracker> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(HandDisinfectionTracker.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static HandDisinfectionTracker load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new HandDisinfectionTracker(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static HandDisinfectionTracker load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new HandDisinfectionTracker(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
