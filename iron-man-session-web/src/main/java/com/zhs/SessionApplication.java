package com.zhs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/2 12:57
 * @package: com.zhs
 * @description:
 */
@SpringBootApplication(scanBasePackages = "com.zhs.*")
public class SessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionApplication.class);
    }
}
