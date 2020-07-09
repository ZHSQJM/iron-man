package com.zhs.auth.kaptcha;


import com.zhs.auth.AuthenticationFailureHandler;
import com.zhs.utils.Contants;
import com.zhs.vo.CaptchaImageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/8 17:13
 * @package: com.zhs.auth
 * @description:
 */
@Component
public class KaptchaCodeFilter extends OncePerRequestFilter {


    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals("/login",request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(),"post")){
             try{
                 validate(new ServletWebRequest(request));
             }catch (AuthenticationException e){
                 authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                 return;
             }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        final HttpSession session = request.getRequest().getSession();
        final String captchaCode = ServletRequestUtils.getStringParameter(request.getRequest(), "captcha");
        if(StringUtils.isEmpty(captchaCode)){
            throw  new SessionAuthenticationException("验证码不能为空");
        }
        final CaptchaImageVo captchaImageVo = (CaptchaImageVo)session.getAttribute(Contants.KAPTCHA_SESSION_CODE);
           if(Objects.isNull(captchaImageVo)){
               throw new SessionAuthenticationException("验证码不存在");
           }
           if(captchaImageVo.isExpired()){
               session.removeAttribute(Contants.KAPTCHA_SESSION_CODE);
               throw new SessionAuthenticationException("验证码已经过期");
           }
           if(!StringUtils.equals(captchaImageVo.getCode(),captchaCode)){
               throw new SessionAuthenticationException("验证码不匹配");
           }
    }
}
