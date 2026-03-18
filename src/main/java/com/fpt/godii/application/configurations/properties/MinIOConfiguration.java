package com.fpt.godii.application.configurations.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "file-server.min-io")
public class MinIOConfiguration {
    private String endpoint;
    private String accessKey;
    private String secretKey;
}
