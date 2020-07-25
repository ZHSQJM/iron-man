package com.zhs.plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhs.plus.dao.SkuInfoMapper;
import com.zhs.plus.entity.SkuInfo;
import com.zhs.plus.service.ISkuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 10:30
 * @package: com.zhs.plus.service.impl
 * @description:
 */
@Slf4j
@Service
public class SkuInfoServiceImpl implements ISkuInfoService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save() {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setCategory("相机");
        return skuInfoMapper.insert(skuInfo);
    }

    @Override
    public List<SkuInfo> query(String category) {
        LambdaQueryWrapper<SkuInfo> lambdaQ = Wrappers.lambdaQuery();
        lambdaQ.like(SkuInfo::getCategory, category);
        return skuInfoMapper.selectList(lambdaQ);
    }
}
