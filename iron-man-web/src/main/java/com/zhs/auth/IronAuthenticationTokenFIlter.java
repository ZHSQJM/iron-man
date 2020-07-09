package com.zhs.auth;

import com.zhs.common.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/9 16:50
 * @package: com.zhs.auth
 * @description:
 */
public class IronAuthenticationTokenFIlter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IronUserDetailsService ironUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String token = request.getHeader(jwtTokenUtil.getHeader());
        if(!StringUtils.isEmpty(token)){
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if(username!=null && SecurityContextHolder.getContext().getAuthentication() ==null){
                final UserDetails userDetails = ironUserDetailsService.loadUserByUsername(username);
                if(jwtTokenUtil.validateToken(token,username)){
                    //没有过期可以
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request,response);
        }

    }
}
