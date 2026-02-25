package com.yexca.config;

import com.yexca.properties.S3OSSProperties;
import com.yexca.utils.S3OSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Configuration;
import java.net.URI;

//import com.yexca.properties.AliOssProperties;
//import com.yexca.utils.AliOssUtil;

@Configuration
@Slf4j
public class OssConfiguration {

//    @Bean
//    @ConditionalOnMissingBean
//    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
//        log.info("开始创建阿里云文件上传工具类对象");
//        return new AliOssUtil(
//                aliOssProperties.getEndpoint(),
//                aliOssProperties.getAccessKeyId(),
//                aliOssProperties.getAccessKeySecret(),
//                aliOssProperties.getBucketName()
//        );
//    }

    @Bean
    @ConditionalOnMissingBean
    public S3OSSUtil s3OSSUtil(S3OSSProperties s3OSSProperties){
        log.info("开始创建 S3 OSS 工具类对象");
        AwsBasicCredentials credentials = AwsBasicCredentials.create(s3OSSProperties.getAccessKeyId(), s3OSSProperties.getAccessKeySecret());
        // 自动补充 endpoint 协议头
        String fullEndpoint = s3OSSProperties.getEndpoint().startsWith("http") ? s3OSSProperties.getEndpoint() : "https://" + s3OSSProperties.getEndpoint();

        S3Client s3Client = S3Client.builder()
                .endpointOverride(URI.create(fullEndpoint))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                // 如果是自建 MinIO 或某些兼容平台，Region 可以填 "auto"
                .region(Region.of(s3OSSProperties.getRegion() != null && !s3OSSProperties.getRegion().isEmpty() ? s3OSSProperties.getRegion() : "us-east-1"))
                // 开启路径风格访问（Path-style），兼容 MinIO 等第三方 S3 服务
                .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
                .build();

        return new S3OSSUtil(s3Client, s3OSSProperties.getBucketName(), s3OSSProperties.getEndpoint());
    }
}
