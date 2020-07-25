package com.zhs.plus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 10:20
 * @package: com.zhs.plus.entity
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuInfo {

    private long id;

    private String category;

    private Double price;

    private String skuDefaultImg;

    private String skuDesc;


    private long spuId;
    private Double weight;
}
