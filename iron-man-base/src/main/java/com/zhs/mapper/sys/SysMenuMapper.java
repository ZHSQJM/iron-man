package com.zhs.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.entity.sys.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hszhou
 * @since 2020-07-06
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    /**
     * 获取用户的权限
     * @param userId
     * @return
     */
    List<SysMenu>  getMenuyByUserId(@Param("userId") Integer userId);

}
