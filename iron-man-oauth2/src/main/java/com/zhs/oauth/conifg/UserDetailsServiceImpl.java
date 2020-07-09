package com.zhs.oauth.conifg;

import com.zhs.entity.sys.SysMenu;
import com.zhs.entity.sys.SysUser;
import com.zhs.service.sys.ISysMenuService;
import com.zhs.service.sys.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/7 18:12
 * @package: com.zhs.oauth.conifg
 * @description:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysMenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = userService.findUserByUserName(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(10);
        if(sysUser !=null){
            final List<SysMenu> menus = menuService.getMenyByUserId(sysUser.getId());
            menus.forEach(e->{
                GrantedAuthority authority = new SimpleGrantedAuthority(e.getCode());
                grantedAuthorities.add(authority);
            });

        }
        return new User(sysUser.getUsername(),sysUser.getPassword(),grantedAuthorities);
    }
}
