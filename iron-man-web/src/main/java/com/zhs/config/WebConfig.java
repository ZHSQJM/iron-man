package com.zhs.config;

import com.zhs.common.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/9 16:17
 * @package: com.zhs.config
 * @description:
 */
@Configuration
public class WebConfig {

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;


    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil(secret,expiration,header);
    }

}
