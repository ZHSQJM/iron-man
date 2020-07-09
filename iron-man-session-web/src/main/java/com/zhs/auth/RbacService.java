package com.zhs.auth;

import com.zhs.entity.sys.SysMenu;
import com.zhs.entity.sys.SysUser;
import com.zhs.service.sys.ISysMenuService;
import com.zhs.service.sys.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/8 13:10
 * @package: com.zhs.auth
 * @description:
 */

@Component(value = "rbacServie")
public class RbacService {


    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysMenuService sysMenuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    /**
     * 判断某个用户是否具有改Request的访问权限
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
         Object principal = authentication.getPrincipal();
         if(principal instanceof  IronUserDetail){
             IronUserDetail userDetail = (IronUserDetail) principal;
             String username = userDetail.getUsername();
             SysUser sysUser = userService.findUserByUserName(username);
             List<String> urls = sysMenuService.getMenyByUserId(sysUser.getId()).stream().map(e -> e.getUrl()).collect(Collectors.toList());
            return urls.stream().anyMatch(url->antPathMatcher.match(url,request.getRequestURI()));
         }
         return false;
    }
}
