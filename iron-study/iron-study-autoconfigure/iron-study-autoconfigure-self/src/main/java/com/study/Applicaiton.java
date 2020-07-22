package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/10 14:58
 * @package: PACKAGE_NAME
 * @description:
 */

@SpringBootApplication
public class Applicaiton {

    public static void main(String[] args) {

        final ConfigurableApplicationContext context = SpringApplication.run(Applicaiton.class);
        final Object user = context.getBean("user");
        System.out.println(user);
    }
}
