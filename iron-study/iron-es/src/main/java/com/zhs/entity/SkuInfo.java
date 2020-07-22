package com.zhs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/17 13:11
 * @package: com.zhs.entity
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SkuInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuName;

    private Double price;

    private Long spuId;

    private Double weight;

    private String  catelog3Id;

    private String skuDefaultImg;

    private String  skuDesc;
}
