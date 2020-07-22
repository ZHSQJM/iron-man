package com.zhs.es.repositroy;

import com.zhs.es.entity.SkuInfoEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/20 13:08
 * @package: com.zhs.es.repositroy
 * @description:
 */
public interface SkuInfoEsRepository extends ElasticsearchRepository<SkuInfoEs,Long> {
}
