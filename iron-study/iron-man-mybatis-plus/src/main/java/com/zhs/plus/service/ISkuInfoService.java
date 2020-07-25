package com.zhs.plus.service;

import com.zhs.plus.entity.SkuInfo;

import java.util.List;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 10:30
 * @package: com.zhs.plus.service
 * @description:
 */
public interface ISkuInfoService {


    int  save();


    List<SkuInfo> query(String category);
}
