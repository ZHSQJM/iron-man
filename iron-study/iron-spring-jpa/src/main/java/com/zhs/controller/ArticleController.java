package com.zhs.controller;

import com.zhs.repositroy.primary.Article;
import com.zhs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/13 11:41
 * @package: com.zhs.controller
 * @description:
 */

@RestController
@RequestMapping("article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @GetMapping
    public Object getList(){
       return articleService.getAll();
    }

    @PostMapping
    public Object post(@RequestBody Article article){
        articleService.saveArticle(article);
         return "SUCCESS";
    }
}
