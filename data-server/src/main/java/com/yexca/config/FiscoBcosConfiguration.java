package com.yexca.config;

import com.yexca.properties.FiscoBcosProperties;
import com.yexca.utils.FiscoBcosUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FiscoBcosConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FiscoBcosUtil fiscoBcosUtil(FiscoBcosProperties fiscoBcosProperties){
        log.info("开始创建区块链工具类对象");
        return new FiscoBcosUtil(
                fiscoBcosProperties.getConfigFile(),
                fiscoBcosProperties.getAbiFilePath(),
                fiscoBcosProperties.getBinFilePath()
        );
    }
}
