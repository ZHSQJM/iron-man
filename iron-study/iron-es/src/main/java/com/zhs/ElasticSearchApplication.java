package com.zhs;

import com.zhs.entity.SkuInfo;
import com.zhs.repositroy.SkuInfoRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/16 20:05
 * @package: com.zhs
 * @description:
 */
@SpringBootApplication
@RestController
public class ElasticSearchApplication {


    @Autowired
    private SkuInfoRepositroy skuInfoRepositroy;

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class);
    }


    @GetMapping("/")
    public SkuInfo get(){
        return skuInfoRepositroy.getOne(1L);
    }
}
