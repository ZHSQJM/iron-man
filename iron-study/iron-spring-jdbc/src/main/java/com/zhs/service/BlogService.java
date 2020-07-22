package com.zhs.service;

import com.zhs.entity.Blog;

import java.util.List;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/13 11:31
 * @package: com.zhs.service
 * @description:
 */
public interface BlogService {


    void save(Blog blog);

    void  delete(String id);

    void update(Blog blog);

    Blog getById(String id);

    List<Blog> getAll();
}
