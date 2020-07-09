package com.zhs.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/6 17:33
 * @package: com.zhs.auth
 * @description:
 */
@Component
@Slf4j
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public AuthenticationFailureHandler(){
        this.setDefaultFailureUrl("/toLogin?error=true");
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //response.setContentType("application/json;charset=UTF-8");
         //response.getWriter().write(objectMapper.writeValueAsString(IronResult.fail(exception.getMessage())));
        String errorMsg ="用户名或密码错误,请重新输入";
        if(exception instanceof SessionAuthenticationException){
            errorMsg = exception.getMessage();
        }
        log.error("获取的错误信息是{}",errorMsg);
        request.setAttribute("errorMsg",errorMsg);
        super.onAuthenticationFailure(request, response, exception);
    }
}
