package com.yexca.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "data-element.elastic-search")
@Data
public class ElasticSearchProperties {
    private String server;
}
