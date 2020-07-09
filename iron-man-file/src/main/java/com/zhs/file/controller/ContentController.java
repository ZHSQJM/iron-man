package com.zhs.file.controller;

import com.zhs.common.IronResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/7 19:46
 * @package: com.zhs.file.controller
 * @description:
 */
@RestController
public class ContentController {


    @Autowired
    private ITbContentService contentService;

    @GetMapping
    public IronResult list(){
      return IronResult.isOk(contentService.list());
    }
}
