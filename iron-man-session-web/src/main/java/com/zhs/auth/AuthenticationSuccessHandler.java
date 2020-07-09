package com.zhs.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/6 17:24
 * @package: com.zhs.auth
 * @description:
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //response.setContentType("application/json;charset=UTF-8");
       // response.getWriter().write(objectMapper.writeValueAsString(IronResult.isOk()));
        super.onAuthenticationSuccess(request,response,authentication);
    }
}
