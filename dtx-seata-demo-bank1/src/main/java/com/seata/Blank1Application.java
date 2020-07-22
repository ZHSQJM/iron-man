package com.seata;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/20 17:06
 * @package: com.zhs
 * @description:
 */
@SpringBootApplication
@MapperScan(basePackages = "com.seata.dao")
@EnableEurekaClient
@EnableFeignClients
public class Blank1Application {

    public static void main(String[] args) {
        SpringApplication.run(Blank1Application.class);
    }


}
