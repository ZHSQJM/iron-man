package com.zhs.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhs.entity.sys.SysRole;
import com.zhs.mapper.sys.SysRoleMapper;
import com.zhs.service.sys.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hszhou
 * @since 2020-07-06
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<String> getRoleCodeByUserName(String username) {
        return sysRoleMapper.getRoleCodeByUsername(username);
    }
}
