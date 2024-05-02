package com.yexca.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "data-element.fisco-ecos")
@Data
public class FiscoBcosProperties {
    private String configFile;

    private String abiFilePath;

    private String binFilePath;
}
