package com.zhs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhs.entity.Blog;
import com.zhs.service.BlogService;
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
@RequestMapping("blog")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @GetMapping
    public Object getList(){
       return blogService.getAll();
    }

    @PostMapping
    public Object post(@RequestBody Blog blog){
         blogService.save(blog);
         return "SUCCESS";
    }
}
