package com.zhs.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/8 09:51
 * @package: com.zhs.blog
 * @description:
 */

@SpringBootApplication(scanBasePackages = "com.zhs.*")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class);
    }
}
