package com.zhs.service;

import com.zhs.entity.SkuInfo;
import org.springframework.stereotype.Service;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/17 13:18
 * @package: com.zhs.service
 * @description:
 */
@Service
public interface SkuInfoService {

    void save(SkuInfo skuInfo);
}
