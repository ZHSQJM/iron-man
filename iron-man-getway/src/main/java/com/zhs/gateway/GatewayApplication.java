package com.zhs.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/4 22:09
 * @package: com.zhs.file
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class GatewayApplication {
    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(GatewayApplication.class);
        final ConfigurableEnvironment environment = app.run(args).getEnvironment();
        log.info("启动成功");
        log.info("file:\t http:127.0.0.1:{}",environment.getProperty("server.port"));
    }
}
