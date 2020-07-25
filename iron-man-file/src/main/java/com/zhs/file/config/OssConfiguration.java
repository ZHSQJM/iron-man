package com.zhs.file.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 16:27
 * @package: com.zhs.file.config
 * @description:
 */
@Data
@AllArgsConstructor
@Configuration
public class OssConfiguration {

    @Value("${aliyun.oss.endpoint}")
    private String endPoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.domain}")
    private String fileHost;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
}
