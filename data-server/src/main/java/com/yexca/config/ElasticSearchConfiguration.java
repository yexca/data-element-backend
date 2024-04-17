package com.yexca.config;

import com.yexca.properties.ElasticSearchProperties;
import com.yexca.utils.ElasticSearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ElasticSearchConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ElasticSearchUtil client(ElasticSearchProperties elasticSearchProperties){
        log.info("开始创建ElasticSearch客户端");
        return new ElasticSearchUtil(elasticSearchProperties.getServer());
    }
}
