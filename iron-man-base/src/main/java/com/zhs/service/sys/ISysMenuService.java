package com.zhs.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhs.entity.sys.SysMenu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hszhou
 * @since 2020-07-06
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<SysMenu> getMenyByUserId(Integer id);

}
