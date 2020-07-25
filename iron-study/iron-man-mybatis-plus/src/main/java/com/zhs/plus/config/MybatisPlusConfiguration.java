package com.zhs.plus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 10:56
 * @package: com.zhs.plus.config
 * @description:
 */

@Configuration
public class MybatisPlusConfiguration {

    //配置分页拦截器
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
