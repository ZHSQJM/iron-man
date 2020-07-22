package com.zhs.mybatis;


import com.zhs.mybatis.dao.primary.Article;
import com.zhs.mybatis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/20 17:06
 * @package: com.zhs
 * @description:
 */
@SpringBootApplication
@RestController
public class MyabtisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyabtisApplication.class);
    }

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public String insert(){
        articleService.save();
        return "SUCCES" ;
    }
}
