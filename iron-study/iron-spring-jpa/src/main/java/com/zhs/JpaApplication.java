package com.zhs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/14 10:43
 * @package: com.zhs
 * @description:
 */
@SpringBootApplication(exclude={
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        })
public class JpaApplication {

    public static void main(String[] args) {

        SpringApplication.run(JpaApplication.class);
    }
}
