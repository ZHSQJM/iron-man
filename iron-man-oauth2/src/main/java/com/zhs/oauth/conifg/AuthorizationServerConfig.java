package com.zhs.oauth.conifg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/3 14:23
 * @package: com.zhs.oauth.conifg
 * @description: 授权认证服务中心配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter {

    //@EnableAuthorizationServer 开启授权认证服务中心

    private static  final int ACCESSTOKENVALIDITYSECONDS = 7200;

    private static  final int REFRESHTOKENVALIDITYSECONDS = 7200;


    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return  DataSourceBuilder.create().build();
    }

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource());
    }

    @Bean
    public ClientDetailsService jdbcClientDetails(){
        return new JdbcClientDetailsService(dataSource());
    }
    /**
     * 配置appid，appkey，回调地址 token有效期
     * @param clients
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        /**
         * 1:授权类型为授权码
         */
//        clients.inMemory().withClient("client").secret(passwordEncoder().encode("secret"))
//                .redirectUris("http://www.zhouhuasheng.top").authorizedGrantTypes("authorization_code").scopes("all")
//        .accessTokenValiditySeconds(ACCESSTOKENVALIDITYSECONDS).refreshTokenValiditySeconds(REFRESHTOKENVALIDITYSECONDS);

        clients.withClientDetails(jdbcClientDetails());
    }

    /**
     * 设置token的类型
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){
        endpoints.tokenStore(tokenStore());
    }

//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer){
//        oauthServer.allowFormAuthenticationForClients();
//        //允许check_token访问
//        oauthServer.checkTokenAccess("permitAll()");
//    }

//    AuthenticationManager authenticationManager(){
//        AuthenticationManager authenticationManager = new AuthenticationManager() {
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                return daoAuthenticationProvider().authenticate(authentication);
//            }
//        };
//        return authenticationManager;
//    }
//
//    @Bean
//    public AuthenticationProvider daoAuthenticationProvider(){
//
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
//        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }

//    @Bean
//    UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(User.withUsername("user_1").password(passwordEncoder().encode("123456")).authorities("ROLE_USER").build());
//        userDetailsManager.createUser(User.withUsername("user_2").password(passwordEncoder().encode("1234567")).authorities("ROLE_USER").build());
//        return userDetailsManager;
//    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
