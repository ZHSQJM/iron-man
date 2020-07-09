package com.zhs.controller;

import com.zhs.auth.IronUserDetail;
import com.zhs.auth.IronUserDetailsService;
import com.zhs.common.IronResult;
import com.zhs.common.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/9 15:34
 * @package: com.zhs.controller
 * @description:
 */

@RestController
@Slf4j
public class HelloController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public IronResult login(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        try{
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }catch (AuthenticationException e){
            return IronResult.fail("用户名密码错误");
        }
        final String token = jwtTokenUtil.generateToken(username);
        return IronResult.isOk(token,"登录成功");
    }

    @GetMapping("/refresh")
    public IronResult refresh(@RequestHeader("${jwt.header}") String token){
        if(!jwtTokenUtil.isTokenExpired(token)){
            return IronResult.isOk(jwtTokenUtil.refreshToken(token),"刷新令牌成功");
        }
            return IronResult.isOk(null);

    }
}
