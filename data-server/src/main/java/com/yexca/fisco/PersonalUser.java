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
public class PersonalUser extends Contract {
    public static final String[] BINARY_ARRAY = {"60806040523480156200001157600080fd5b506110016000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166356004b6a6040805190810160405280600d81526020017f706572736f6e616c5f75736572000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016200010191906200024a565b602060405180830381600087803b1580156200011c57600080fd5b505af115801562000131573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525062000157919081019062000174565b50620002f4565b60006200016c8251620002a3565b905092915050565b6000602082840312156200018757600080fd5b600062000197848285016200015e565b91505092915050565b6000620001ad8262000298565b808452620001c3816020860160208601620002ad565b620001ce81620002e3565b602085010191505092915050565b6000600682527f75736572496400000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600882527f757365726e616d650000000000000000000000000000000000000000000000006020830152604082019050919050565b60006060820190508181036000830152620002668184620001a0565b905081810360208301526200027b81620001dc565b90508181036040830152620002908162000213565b905092915050565b600081519050919050565b6000819050919050565b60005b83811015620002cd578082015181840152602081019050620002b0565b83811115620002dd576000848401525b50505050565b6000601f19601f8301169050919050565b61110780620003046000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806311dd884514610051578063c95962481461008e575b600080fd5b34801561005d57600080fd5b5061007860048036036100739190810190610b9a565b6100cb565b6040516100859190610e2d565b60405180910390f35b34801561009a57600080fd5b506100b560048036036100b09190810190610c1c565b61061e565b6040516100c29190610dd5565b60405180910390f35b606060008060008060606000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040805190810160405280600d81526020017f706572736f6e616c5f75736572000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016101809190610e0b565b602060405180830381600087803b15801561019a57600080fd5b505af11580156101ae573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506101d29190810190610b48565b94508473ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561023857600080fd5b505af115801561024c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506102709190810190610acd565b93508373ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1886040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016102c79190610eaf565b600060405180830381600087803b1580156102e157600080fd5b505af11580156102f5573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663e8434e3988866040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610350929190610e4f565b602060405180830381600087803b15801561036a57600080fd5b505af115801561037e573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506103a29190810190610af6565b925060008373ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561040a57600080fd5b505af115801561041e573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506104429190810190610b71565b131515610484576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161047b90610f39565b60405180910390fd5b8273ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016104da9190610df0565b602060405180830381600087803b1580156104f457600080fd5b505af1158015610508573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061052c9190810190610b1f565b91508173ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161058190610ee4565b600060405180830381600087803b15801561059b57600080fd5b505af11580156105af573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506105d89190810190610bdb565b90507f4c11eb21bb6bb1a8297ccd2d458b6fce27fb33a43fc9f736faac366bc2222932816040516106099190610e2d565b60405180910390a18095505050505050919050565b6000806000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040805190810160405280600d81526020017f706572736f6e616c5f75736572000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016106cf9190610e0b565b602060405180830381600087803b1580156106e957600080fd5b505af11580156106fd573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506107219190810190610b48565b92508273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561078757600080fd5b505af115801561079b573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506107bf9190810190610b1f565b91508173ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016108169190610eaf565b600060405180830381600087803b15801561083057600080fd5b505af1158015610844573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516866040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161089d9190610f04565b600060405180830381600087803b1580156108b757600080fd5b505af11580156108cb573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166331afac3687846040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610926929190610e7f565b602060405180830381600087803b15801561094057600080fd5b505af1158015610954573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506109789190810190610b71565b90507f8bb16f43c5d33032286e3f8a2c3fbcf9c635d80a71884165dee4063e8c82be4f816040516109a99190610dd5565b60405180910390a180935050505092915050565b60006109c98251610ff2565b905092915050565b60006109dd8251611004565b905092915050565b60006109f18251611016565b905092915050565b6000610a058251611028565b905092915050565b6000610a19825161103a565b905092915050565b600082601f8301121515610a3457600080fd5b8135610a47610a4282610f86565b610f59565b91508082526020830160208301858383011115610a6357600080fd5b610a6e83828461107a565b50505092915050565b600082601f8301121515610a8a57600080fd5b8151610a9d610a9882610f86565b610f59565b91508082526020830160208301858383011115610ab957600080fd5b610ac4838284611089565b50505092915050565b600060208284031215610adf57600080fd5b6000610aed848285016109bd565b91505092915050565b600060208284031215610b0857600080fd5b6000610b16848285016109d1565b91505092915050565b600060208284031215610b3157600080fd5b6000610b3f848285016109e5565b91505092915050565b600060208284031215610b5a57600080fd5b6000610b68848285016109f9565b91505092915050565b600060208284031215610b8357600080fd5b6000610b9184828501610a0d565b91505092915050565b600060208284031215610bac57600080fd5b600082013567ffffffffffffffff811115610bc657600080fd5b610bd284828501610a21565b91505092915050565b600060208284031215610bed57600080fd5b600082015167ffffffffffffffff811115610c0757600080fd5b610c1384828501610a77565b91505092915050565b60008060408385031215610c2f57600080fd5b600083013567ffffffffffffffff811115610c4957600080fd5b610c5585828601610a21565b925050602083013567ffffffffffffffff811115610c7257600080fd5b610c7e85828601610a21565b9150509250929050565b610c9181611044565b82525050565b610ca081611056565b82525050565b610caf81610fe8565b82525050565b610cbe81611068565b82525050565b6000610ccf82610fbd565b808452610ce3816020860160208601611089565b610cec816110bc565b602085010191505092915050565b60","00610d0582610fb2565b808452610d19816020860160208601611089565b610d22816110bc565b602085010191505092915050565b6000600682527f75736572496400000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600882527f757365726e616d650000000000000000000000000000000000000000000000006020830152604082019050919050565b6000601082527f5265636f7264206e6f74206578697374000000000000000000000000000000006020830152604082019050919050565b6000602082019050610dea6000830184610ca6565b92915050565b6000602082019050610e056000830184610cb5565b92915050565b60006020820190508181036000830152610e258184610cfa565b905092915050565b60006020820190508181036000830152610e478184610cc4565b905092915050565b60006040820190508181036000830152610e698185610cc4565b9050610e786020830184610c88565b9392505050565b60006040820190508181036000830152610e998185610cc4565b9050610ea86020830184610c97565b9392505050565b60006040820190508181036000830152610ec881610d30565b90508181036020830152610edc8184610cc4565b905092915050565b60006020820190508181036000830152610efd81610d67565b9050919050565b60006040820190508181036000830152610f1d81610d67565b90508181036020830152610f318184610cc4565b905092915050565b60006020820190508181036000830152610f5281610d9e565b9050919050565b6000604051905081810181811067ffffffffffffffff82111715610f7c57600080fd5b8060405250919050565b600067ffffffffffffffff821115610f9d57600080fd5b601f19601f8301169050602081019050919050565b600081519050919050565b600081519050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b6000610ffd82610fc8565b9050919050565b600061100f82610fc8565b9050919050565b600061102182610fc8565b9050919050565b600061103382610fc8565b9050919050565b6000819050919050565b600061104f82610fc8565b9050919050565b600061106182610fc8565b9050919050565b600061107382610fe8565b9050919050565b82818337600083830152505050565b60005b838110156110a757808201518184015260208101905061108c565b838111156110b6576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a72305820c75f5507291f85d78fd4f861f2f6f2e95ea807f2da827829020df3f7958944376c6578706572696d656e74616cf50037"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"userId\",\"type\":\"string\"}],\"name\":\"getRecord\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"userId\",\"type\":\"string\"},{\"name\":\"username\",\"type\":\"string\"}],\"name\":\"addRecord\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"userId\",\"type\":\"int256\"}],\"name\":\"AddRecordResult\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"username\",\"type\":\"string\"}],\"name\":\"GetRecordResult\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_GETRECORD = "getRecord";

    public static final String FUNC_ADDRECORD = "addRecord";

    public static final Event ADDRECORDRESULT_EVENT = new Event("AddRecordResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event GETRECORDRESULT_EVENT = new Event("GetRecordResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    protected PersonalUser(String contractAddress, Client client, CryptoKeyPair credential) {
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

    public Tuple1<String> getGetRecordOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_GETRECORD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt addRecord(String userId, String username) {
        final Function function = new Function(
                FUNC_ADDRECORD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userId), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(username)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] addRecord(String userId, String username, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDRECORD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userId), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(username)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAddRecord(String userId, String username) {
        final Function function = new Function(
                FUNC_ADDRECORD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userId), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(username)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getAddRecordInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDRECORD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
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
            typedResponse.userId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
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

    public static PersonalUser load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new PersonalUser(contractAddress, client, credential);
    }

    public static PersonalUser deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(PersonalUser.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class AddRecordResultEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger userId;
    }

    public static class GetRecordResultEventResponse {
        public TransactionReceipt.Logs log;

        public String username;
    }
}
