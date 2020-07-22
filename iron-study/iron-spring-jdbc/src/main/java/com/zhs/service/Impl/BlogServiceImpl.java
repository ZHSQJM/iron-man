package com.zhs.service.Impl;

import com.zhs.dao.BlogDao;
import com.zhs.entity.Blog;
import com.zhs.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/13 11:34
 * @package: com.zhs.service.Impl
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public void save(Blog blog) {
        blogDao.save(blog);
        blogDao.savetwo(blog);
        int a = 10/0;
    }

    @Override
    public void delete(String id) {
        blogDao.deleteById(id);
    }

    @Override
    public void update(Blog blog) {
        blogDao.save(blog);
    }

    @Override
    public Blog getById(String id) {
        return blogDao.findById(id);
    }

    @Override
    public List<Blog> getAll() {
        return blogDao.findAll();
    }
}
