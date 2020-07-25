package com.zhs.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 10:37
 * @package: com.zhs.plus
 * @description:
 */
@SpringBootApplication
@MapperScan("com.zhs.plus.dao")
public class PlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlusApplication.class);
    }
}
