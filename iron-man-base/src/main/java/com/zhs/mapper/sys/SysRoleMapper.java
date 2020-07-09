package com.zhs.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.entity.sys.SysRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hszhou
 * @since 2020-07-06
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {


    List<String> getRoleCodeByUsername(String username);
}
