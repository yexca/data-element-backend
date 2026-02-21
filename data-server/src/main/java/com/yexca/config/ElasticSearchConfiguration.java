package com.yexca.config;

import com.yexca.properties.ElasticSearchProperties;
import com.yexca.utils.ElasticSearchUtil;
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
    public ElasticSearchUtil client(ElasticSearchProperties elasticSearchProperties){
        log.info("开始创建ElasticSearch客户端");
        ElasticSearchUtil util = new ElasticSearchUtil(
                new RestHighLevelClient(RestClient.builder(HttpHost.create(
                        elasticSearchProperties.getServer()
                ))));
        // 初始化逻辑检查
        initIndex(util);

        return util;
    }

    /**
     * 判断是否存在对应的索引库，不存在则创建
     * @param util
     */
    private void initIndex(ElasticSearchUtil util){
        if (!util.existsIndex()){
            log.info("索引库不存在，开始创建");
            util.createIndex();
            log.info("索引库创建成功");
        }else {
            log.info("索引库存在，跳过初始化");
        }
    }
}
