package com.zhs.service.file.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhs.entity.file.IronFile;
import com.zhs.mapper.file.IronFileMapper;
import com.zhs.service.file.IIronFileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hszhou
 * @since 2020-07-05
 */
@Service
public class IronFileServiceImpl extends ServiceImpl<IronFileMapper, IronFile> implements IIronFileService {

    @Override
    public boolean save(IronFile entity) {
        final IronFile ironFile = selectByKey(entity.getKey1());
        if(ironFile == null){
            return super.save(entity);
        }else{
            ironFile.setShardIndex(entity.getShardIndex());
        return    super.updateById(ironFile);
        }

    }

    private IronFile selectByKey(String key){

        QueryWrapper<IronFile> queryWrapper = new QueryWrapper();
        queryWrapper.eq("key1",key);
        IronFile ironFile = this.baseMapper.selectOne(queryWrapper);
        return ironFile!=null?ironFile:null;
    }
}
