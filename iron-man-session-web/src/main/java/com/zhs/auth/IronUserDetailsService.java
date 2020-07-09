package com.zhs.auth;

import com.zhs.entity.sys.SysMenu;
import com.zhs.entity.sys.SysUser;
import com.zhs.mapper.sys.SysMenuMapper;
import com.zhs.mapper.sys.SysUserMapper;
import com.zhs.service.sys.ISysMenuService;
import com.zhs.service.sys.ISysRoleService;
import com.zhs.service.sys.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/8 11:31
 * @package: com.zhs.auth
 * @description:
 */
@Service
public class IronUserDetailsService implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

          IronUserDetail ironUserDetail = new IronUserDetail();
         SysUser sysUser = sysUserService.findUserByUserName(username);
         if(sysUser==null){
             throw  new RuntimeException();
         }
         ironUserDetail.setUsername(sysUser.getUsername());
         ironUserDetail.setPassword(sysUser.getPassword());


        //加载角色列表
        List<String> roleCodeByUserName = sysRoleService.getRoleCodeByUserName(username);
        List<String> authorities = roleCodeByUserName.stream().map(e -> "ROLE_" + e).collect(Collectors.toList());
        //加载权限
        List<SysMenu> sysMenus = sysMenuService.getMenyByUserId(sysUser.getId());
        List<String> collect = sysMenus.stream().map(e -> e.getUrl()).collect(Collectors.toList());
        authorities.addAll(collect);
        ironUserDetail.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",",authorities)));
        return ironUserDetail;
    }
}
