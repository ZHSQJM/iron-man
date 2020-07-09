package com.zhs.config;

import com.zhs.auth.AuthenticationFailureHandler;
import com.zhs.auth.AuthenticationSuccessHandler;
import com.zhs.auth.IronUserDetailsService;
import com.zhs.auth.sms.SmsCodeAuthenticationFilter;
import com.zhs.auth.sms.SmsCodeAuthenticationProvider;
import com.zhs.auth.sms.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/9 13:32
 * @package: com.zhs.config
 * @description:
 */
@Component
public class SmsSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {



    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    IronUserDetailsService ironUserDetailsService;

    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(ironUserDetailsService);

        builder.addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class);
        builder.authenticationProvider(smsCodeAuthenticationProvider).addFilterAfter(smsCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
        super.configure(builder);
    }
}
