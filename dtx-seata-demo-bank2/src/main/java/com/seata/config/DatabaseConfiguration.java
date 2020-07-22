package com.seata.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/22 16:22
 * @package: com.seata.config
 * @description:
 */
@Configuration
public class DatabaseConfiguration {

    private final ApplicationContext applicationContext;


    public DatabaseConfiguration(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource ds0(){
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
}
