package com.yexca.config;

import com.yexca.properties.ElasticSearchProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ElasticSearchConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public RestHighLevelClient restHighLevelClient(ElasticSearchProperties elasticSearchProperties){
        log.info("开始创建ElasticSearch客户端");
        return new RestHighLevelClient(
                RestClient.builder(
                        HttpHost.create(
                                elasticSearchProperties.getServer()
                        )
                )
        );
    }
}
