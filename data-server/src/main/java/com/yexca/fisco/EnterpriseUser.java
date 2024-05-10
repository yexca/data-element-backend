package com.yexca.fisco;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Int256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class EnterpriseUser extends Contract {
    public static final String[] BINARY_ARRAY = {"60806040523480156200001157600080fd5b506110016000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166356004b6a6040805190810160405280600f81526020017f656e74657270726973655f7573657200000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016200010191906200024a565b602060405180830381600087803b1580156200011c57600080fd5b505af115801562000131573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525062000157919081019062000174565b50620002f4565b60006200016c8251620002a3565b905092915050565b6000602082840312156200018757600080fd5b600062000197848285016200015e565b91505092915050565b6000620001ad8262000298565b808452620001c3816020860160208601620002ad565b620001ce81620002e3565b602085010191505092915050565b6000600682527f75736572496400000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000601882527f757365726e616d652c20656e74657270726973654e616d6500000000000000006020830152604082019050919050565b60006060820190508181036000830152620002668184620001a0565b905081810360208301526200027b81620001dc565b90508181036040830152620002908162000213565b905092915050565b600081519050919050565b6000819050919050565b60005b83811015620002cd578082015181840152602081019050620002b0565b83811115620002dd576000848401525b50505050565b6000601f19601f8301169050919050565b61131080620003046000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806311dd8845146100515780633f723c621461008f575b600080fd5b34801561005d57600080fd5b5061007860048036036100739190810190610cd7565b6100cc565b60405161008692919061102c565b60405180910390f35b34801561009b57600080fd5b506100b660048036036100b19190810190610d59565b6106d3565b6040516100c39190610f74565b60405180910390f35b6060806000806000806060806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040805190810160405280600f81526020017f656e74657270726973655f7573657200000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016101839190610faa565b602060405180830381600087803b15801561019d57600080fd5b505af11580156101b1573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506101d59190810190610c85565b95508573ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561023b57600080fd5b505af115801561024f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506102739190810190610c0a565b94508473ffffffffffffffffffffffffffffffffffffffff1663cd30a1d18a6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016102ca91906110b8565b600060405180830381600087803b1580156102e457600080fd5b505af11580156102f8573d6000803e3d6000fd5b505050508573ffffffffffffffffffffffffffffffffffffffff1663e8434e398a876040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610353929190610fcc565b602060405180830381600087803b15801561036d57600080fd5b505af1158015610381573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506103a59190810190610c33565b935060008473ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561040d57600080fd5b505af1158015610421573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506104459190810190610cae565b131515610487576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161047e90611142565b60405180910390fd5b8373ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016104dd9190610f8f565b602060405180830381600087803b1580156104f757600080fd5b505af115801561050b573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061052f9190810190610c5c565b92508273ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610584906110ed565b600060405180830381600087803b15801561059e57600080fd5b505af11580156105b2573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506105db9190810190610d18565b91508273ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161063090611063565b600060405180830381600087803b15801561064a57600080fd5b505af115801561065e573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506106879190810190610d18565b90507f053e7eb7bba6ff297c73063cfa0bc95e41307ba0fc481ee415c0354a914cb9fb82826040516106ba92919061102c565b60405180910390a1818197509750505050505050915091565b6000806000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040805190810160405280600f81526020017f656e74657270726973655f7573657200000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016107849190610faa565b602060405180830381600087803b15801561079e57600080fd5b505af11580156107b2573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506107d69190810190610c85565b92508273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561083c57600080fd5b505af1158015610850573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506108749190810190610c5c565b91508173ffffffffffffffffffffffffffffffffffffffff1663e942b516886040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016108cb91906110b8565b600060405180830381600087803b1580156108e557600080fd5b505af11580156108f9573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610952919061110d565b600060405180830381600087803b15801561096c57600080fd5b505af1158015610980573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516866040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016109d99190611083565b600060405180830381600087803b1580156109f357600080fd5b505af1158015610a07573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166331afac3688846040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610a62929190610ffc565b602060405180830381600087803b158015610a7c57600080fd5b505af1158015610a90573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610ab49190810190610cae565b90507f8bb16f43c5d33032286e3f8a2c3fbcf9c635d80a71884165dee4063e8c82be4f81604051610ae59190610f74565b60405180910390a18093505050509392505050565b6000610b0682516111fb565b905092915050565b6000610b1a825161120d565b905092915050565b6000610b2e825161121f565b905092915050565b6000610b428251611231565b905092915050565b6000610b568251611243565b905092915050565b600082601f8301121515610b7157600080fd5b8135610b84610b7f8261118f565b611162565b91508082526020830160208301858383011115610ba057600080fd5b610bab838284611283565b50505092915050565b600082601f8301121515610bc757600080fd5b8151610bda610bd58261118f565b611162565b91508082526020830160208301858383011115610bf657600080fd5b610c01838284611292565b50505092915050565b600060208284031215610c1c57600080fd5b6000610c2a84828501610afa565b91505092915050565b600060208284031215610c4557600080fd5b6000610c5384828501610b0e565b91505092915050565b600060208284031215610c6e57600080fd5b6000610c7c84828501610b22565b91505092915050565b600060208284031215610c9757600080fd5b6000610ca584828501610b36565b91505092915050565b600060208284031215610cc057600080fd5b6000610cce84828501610b4a565b91505092915050565b600060208284031215610ce957600080fd5b600082013567ffffffffffffffff81111561","0d0357600080fd5b610d0f84828501610b5e565b91505092915050565b600060208284031215610d2a57600080fd5b600082015167ffffffffffffffff811115610d4457600080fd5b610d5084828501610bb4565b91505092915050565b600080600060608486031215610d6e57600080fd5b600084013567ffffffffffffffff811115610d8857600080fd5b610d9486828701610b5e565b935050602084013567ffffffffffffffff811115610db157600080fd5b610dbd86828701610b5e565b925050604084013567ffffffffffffffff811115610dda57600080fd5b610de686828701610b5e565b9150509250925092565b610df98161124d565b82525050565b610e088161125f565b82525050565b610e17816111f1565b82525050565b610e2681611271565b82525050565b6000610e37826111c6565b808452610e4b816020860160208601611292565b610e54816112c5565b602085010191505092915050565b6000610e6d826111bb565b808452610e81816020860160208601611292565b610e8a816112c5565b602085010191505092915050565b6000600e82527f656e74657270726973654e616d650000000000000000000000000000000000006020830152604082019050919050565b6000600682527f75736572496400000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600882527f757365726e616d650000000000000000000000000000000000000000000000006020830152604082019050919050565b6000601082527f5265636f7264206e6f74206578697374000000000000000000000000000000006020830152604082019050919050565b6000602082019050610f896000830184610e0e565b92915050565b6000602082019050610fa46000830184610e1d565b92915050565b60006020820190508181036000830152610fc48184610e62565b905092915050565b60006040820190508181036000830152610fe68185610e2c565b9050610ff56020830184610df0565b9392505050565b600060408201905081810360008301526110168185610e2c565b90506110256020830184610dff565b9392505050565b600060408201905081810360008301526110468185610e2c565b9050818103602083015261105a8184610e2c565b90509392505050565b6000602082019050818103600083015261107c81610e98565b9050919050565b6000604082019050818103600083015261109c81610e98565b905081810360208301526110b08184610e2c565b905092915050565b600060408201905081810360008301526110d181610ecf565b905081810360208301526110e58184610e2c565b905092915050565b6000602082019050818103600083015261110681610f06565b9050919050565b6000604082019050818103600083015261112681610f06565b9050818103602083015261113a8184610e2c565b905092915050565b6000602082019050818103600083015261115b81610f3d565b9050919050565b6000604051905081810181811067ffffffffffffffff8211171561118557600080fd5b8060405250919050565b600067ffffffffffffffff8211156111a657600080fd5b601f19601f8301169050602081019050919050565b600081519050919050565b600081519050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b6000611206826111d1565b9050919050565b6000611218826111d1565b9050919050565b600061122a826111d1565b9050919050565b600061123c826111d1565b9050919050565b6000819050919050565b6000611258826111d1565b9050919050565b600061126a826111d1565b9050919050565b600061127c826111f1565b9050919050565b82818337600083830152505050565b60005b838110156112b0578082015181840152602081019050611295565b838111156112bf576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a72305820b42ffafc4920ea7625107ad1593ab46de5887643b22d17e05d7cce2ac5cb90566c6578706572696d656e74616cf50037"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"userId\",\"type\":\"string\"}],\"name\":\"getRecord\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"userId\",\"type\":\"string\"},{\"name\":\"username\",\"type\":\"string\"},{\"name\":\"enterpriseName\",\"type\":\"string\"}],\"name\":\"addRecord\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"AddRecordResult\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"username\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"enterpriseName\",\"type\":\"string\"}],\"name\":\"GetRecordResult\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_GETRECORD = "getRecord";

    public static final String FUNC_ADDRECORD = "addRecord";

    public static final Event ADDRECORDRESULT_EVENT = new Event("AddRecordResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event GETRECORDRESULT_EVENT = new Event("GetRecordResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected EnterpriseUser(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt getRecord(String userId) {
        final Function function = new Function(
                FUNC_GETRECORD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] getRecord(String userId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_GETRECORD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userId)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForGetRecord(String userId) {
        final Function function = new Function(
                FUNC_GETRECORD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userId)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getGetRecordInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_GETRECORD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple2<String, String> getGetRecordOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_GETRECORD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public TransactionReceipt addRecord(String userId, String username, String enterpriseName) {
        final Function function = new Function(
                FUNC_ADDRECORD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userId), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(username), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(enterpriseName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] addRecord(String userId, String username, String enterpriseName, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDRECORD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userId), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(username), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(enterpriseName)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAddRecord(String userId, String username, String enterpriseName) {
        final Function function = new Function(
                FUNC_ADDRECORD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userId), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(username), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(enterpriseName)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<String, String, String> getAddRecordInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDRECORD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public Tuple1<BigInteger> getAddRecordOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_ADDRECORD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public List<AddRecordResultEventResponse> getAddRecordResultEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDRECORDRESULT_EVENT, transactionReceipt);
        ArrayList<AddRecordResultEventResponse> responses = new ArrayList<AddRecordResultEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddRecordResultEventResponse typedResponse = new AddRecordResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeAddRecordResultEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(ADDRECORDRESULT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeAddRecordResultEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(ADDRECORDRESULT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<GetRecordResultEventResponse> getGetRecordResultEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(GETRECORDRESULT_EVENT, transactionReceipt);
        ArrayList<GetRecordResultEventResponse> responses = new ArrayList<GetRecordResultEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            GetRecordResultEventResponse typedResponse = new GetRecordResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.username = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.enterpriseName = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeGetRecordResultEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(GETRECORDRESULT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeGetRecordResultEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(GETRECORDRESULT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public static EnterpriseUser load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new EnterpriseUser(contractAddress, client, credential);
    }

    public static EnterpriseUser deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(EnterpriseUser.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class AddRecordResultEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger count;
    }

    public static class GetRecordResultEventResponse {
        public TransactionReceipt.Logs log;

        public String username;

        public String enterpriseName;
    }
}
