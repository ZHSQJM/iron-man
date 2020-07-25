package com.zhs.config;

import com.zhs.auth.ExpiredSessionStrategy;
import com.zhs.auth.IronLogoutSuccessHandler;
import com.zhs.auth.IronUserDetailsService;
import com.zhs.auth.kaptcha.KaptchaCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/6 15:41
 * @package: com.zhs.config
 * @description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 自定义登录成功
     */
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 自定义登录失败
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 超过会话个数
     */
    @Autowired
    private ExpiredSessionStrategy expiredSessionStrategy;

    /**
     * 自定义登录认证
     */
    @Autowired
    private IronUserDetailsService userDetailsService;

    /**
     * 自定义退出
     */
   @Autowired
    private IronLogoutSuccessHandler logoutSuccessHandler;

    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 验证码
     */
  @Autowired
  private KaptchaCodeFilter kaptchaCodeFilter;

  @Autowired
  private SmsSecurityConfig smsSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //ifreame版本报错误
        http.headers().frameOptions().disable();

        //在登陆之前调用验证码
        http.addFilterBefore(kaptchaCodeFilter,UsernamePasswordAuthenticationFilter.class);

        //退出登录
        http.logout().logoutSuccessHandler(logoutSuccessHandler);

        //记住我
        http.rememberMe().rememberMeParameter("remember-me")
                .rememberMeCookieName("iron-man-cookies")
                .tokenValiditySeconds(5*24*60*60)
                .tokenRepository(persistentTokenRepository());

      http.csrf().disable().formLogin()
               .loginPage("/toLogin")
               .loginProcessingUrl("/login")
               .successHandler(authenticationSuccessHandler)
               .failureHandler(authenticationFailureHandler)
               .and()
              .apply(smsSecurityConfig).and()
               .authorizeRequests()
               .antMatchers("/toLogin","/login","/kaptcha","/sms","/smsLogin").permitAll()
               .antMatchers("/","/main").authenticated()
               .anyRequest().access("@rbacServie.hasPermission(request,authentication)")
//               .antMatchers("/biz1","/biz2")
//               .hasAnyAuthority("ROLE_user","ROLE_admin")
//               .antMatchers("/syslog","/sysuser")
//               .hasAnyRole("admin")
              // .antMatchers("/syslog").hasAuthority("sys:log")
              // .antMatchers("/sysuser").hasAuthority("sys:user")
              // .anyRequest().authenticated().
                //配置session过期之后跳转登录页面
             .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/toLogin")
               .sessionFixation().migrateSession()
               .maximumSessions(1).maxSessionsPreventsLogin(false).expiredSessionStrategy(expiredSessionStrategy);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user")
//                .password(passwordEncoder().encode("123456")).roles("user")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder().encode("123456"))
//                .roles("admin")
//                //.authorities("sys:log","sys:user")
//                .and()
//                .passwordEncoder(passwordEncoder());
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/images/**","/fonts/**");
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
       // tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
