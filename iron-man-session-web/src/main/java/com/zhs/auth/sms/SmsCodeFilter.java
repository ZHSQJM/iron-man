package com.zhs.auth.sms;

import com.zhs.auth.AuthenticationFailureHandler;
import com.zhs.entity.sys.SysUser;
import com.zhs.service.sys.ISysUserService;
import com.zhs.utils.Contants;
import com.zhs.vo.CaptchaImageVo;
import com.zhs.vo.SmsCodeVo;
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
 * @date: 2020/7/9 10:26
 * @package: com.zhs.auth
 * @description:
 */
@Component
public class SmsCodeFilter extends OncePerRequestFilter {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals("/smsLogin", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            try {
                validate(new ServletWebRequest(request));
            } catch (AuthenticationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        final HttpSession session = request.getRequest().getSession();
        final String smsCode = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");
        final String telephone = ServletRequestUtils.getStringParameter(request.getRequest(), "telephone");
        if (StringUtils.isEmpty(telephone)) {
            throw new SessionAuthenticationException("手机号码不能为空");
        }
        if (StringUtils.isEmpty(smsCode)) {
            throw new SessionAuthenticationException("短信验证码不能为空");
        }
        final SmsCodeVo smsCodeVo = (SmsCodeVo) session.getAttribute(Contants.TELEPHONE_SESSION_CODE);
        if (Objects.isNull(smsCodeVo)) {
            throw new SessionAuthenticationException("短信验证码不存在");
        }
        if (smsCodeVo.isExpired()) {
            session.removeAttribute(Contants.TELEPHONE_SESSION_CODE);
            throw new SessionAuthenticationException("短信验证码已经过期");
        }
        if (!StringUtils.equals(smsCodeVo.getCode(), smsCode)) {
            throw new SessionAuthenticationException("短信验证码不匹配");
        }
         SysUser user = sysUserService.findUserByUserName(telephone);
         if(user==null){
             throw  new SessionAuthenticationException("用户不存在");
         }
    }
}