package com.zhs.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhs.entity.sys.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hszhou
 * @since 2020-07-06
 */
public interface ISysUserService extends IService<SysUser> {


    SysUser findUserByUserName(String username);

}
