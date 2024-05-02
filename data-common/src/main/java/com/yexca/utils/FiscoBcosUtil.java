package com.yexca.utils;

import com.yexca.constant.ContractConstant;
import com.yexca.constant.MessageConstant;
import com.yexca.exception.FiscoBcosException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;

import java.util.List;

@Data
@AllArgsConstructor
@Slf4j
public class FiscoBcosUtil {
    private String configFile;
    private String abiFilePath;
    private String binFilePath;

    public List<Object> add(List<Object> params, String contractName, String contractAddress){
        // 初始化BcosSDK对象
        BcosSDK sdk = BcosSDK.build(configFile);
        // 获取Client对象，此处传入的群组ID为1
        Client client = sdk.getClient(Integer.valueOf(1));
        // 构造AssembleTransactionProcessor对象，需要传入client对象，CryptoKeyPair对象和abi、binary文件存放的路径。abi和binary文件需要在上一步复制到定义的文件夹中。
        CryptoKeyPair keyPair = client.getCryptoSuite().createKeyPair();
        try {
            // 初始化配置对象，仅交易和查询
            AssembleTransactionProcessor transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(client, keyPair, abiFilePath, binFilePath);
            // 调用PersonalUser合约，合约地址为address， 调用函数名为『addRecord』，函数参数类型为params
            TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader(contractName, contractAddress, ContractConstant.ADD, params);

            // 返回值
            List<Object> returnObjects = transactionResponse.getReturnObject();
            return returnObjects;

        } catch (Exception e) {
            throw new FiscoBcosException(MessageConstant.FISCO_BCOS_ERROR);
        }
    }

    public List<Object> get(List<Object> params, String contractName, String contractAddress){
        // 初始化BcosSDK对象
        BcosSDK sdk = BcosSDK.build(configFile);
        // 获取Client对象，此处传入的群组ID为1
        Client client = sdk.getClient(Integer.valueOf(1));
        // 构造AssembleTransactionProcessor对象，需要传入client对象，CryptoKeyPair对象和abi、binary文件存放的路径。abi和binary文件需要在上一步复制到定义的文件夹中。
        CryptoKeyPair keyPair = client.getCryptoSuite().createKeyPair();
        try {
            // 初始化配置对象，仅交易和查询
            AssembleTransactionProcessor transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(client, keyPair, abiFilePath, binFilePath);
            // 调用PersonalUser合约，合约地址为address， 调用函数名为『addRecord』，函数参数类型为params
            TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader(contractName, contractAddress, ContractConstant.GET, params);

            // 返回值
            List<Object> returnObjects = transactionResponse.getReturnObject();
            return returnObjects;

        } catch (Exception e) {
            throw new FiscoBcosException(MessageConstant.FISCO_BCOS_ERROR);
        }
    }
}
