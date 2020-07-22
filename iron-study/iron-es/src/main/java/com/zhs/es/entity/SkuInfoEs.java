package com.zhs.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/20 13:05
 * @package: com.zhs
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "mall",type = "skuinfo")
public class SkuInfoEs {

    private Long id;

    private String skuName;

    private Double price;

    private Long spuId;

    private Double weight;

    private String  catelog3Id;

    private String skuDefaultImg;

    private String  skuDesc;
}
