package com.zhs.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/4 21:41
 * @package: com.zhs.eureka
 * @description:
 */
@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaApplication {

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(EurekaApplication.class);
        final ConfigurableEnvironment environment = app.run(args).getEnvironment();
        log.info("启动成功");
        log.info("eureka地址:\t http:127.0.0.1:{}",environment.getProperty("server.port"));
    }
}
