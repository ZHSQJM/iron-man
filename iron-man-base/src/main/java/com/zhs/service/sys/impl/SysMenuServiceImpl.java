package com.zhs.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhs.entity.sys.SysMenu;
import com.zhs.mapper.sys.SysMenuMapper;
import com.zhs.service.sys.ISysMenuService;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getMenyByUserId(Integer userId) {
        return sysMenuMapper.getMenuyByUserId(userId);
    }
}
