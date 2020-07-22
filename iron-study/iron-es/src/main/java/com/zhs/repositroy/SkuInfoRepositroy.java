package com.zhs.repositroy;

import com.zhs.entity.SkuInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/17 13:17
 * @package: com.zhs.repositroy
 * @description:
 */
@Repository
public interface SkuInfoRepositroy extends JpaRepository<SkuInfo,Long> {
}
