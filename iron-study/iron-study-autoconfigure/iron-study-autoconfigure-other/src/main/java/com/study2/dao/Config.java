package com.study2.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/10 14:57
 * @package: com.study.dao
 * @description:
 */
@Configuration
public class Config {


    @Bean
    public User user(){
        return new User();
    }
}
