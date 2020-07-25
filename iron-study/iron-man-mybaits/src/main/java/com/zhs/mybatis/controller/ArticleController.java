package com.zhs.mybatis.controller;

import cn.shuibo.annotation.Encrypt;
import com.zhs.mybatis.annonation.WebLog;
import com.zhs.mybatis.dao.primary.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 16:06
 * @package: com.zhs.mybatis.controller
 * @description:
 */
@RestController
@RequestMapping("article")
public class ArticleController {


    @Encrypt
    @GetMapping("/encryption")
    @WebLog(value = "获取请求")
    public Article encryption(){
        Article testBean = new Article();
        testBean.setAuthor("周华生");
        testBean.setContent("这是一个内容");
        return testBean;
    }
}
