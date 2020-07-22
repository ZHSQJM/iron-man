package com.zhs.service.impl;

import com.zhs.repositroy.primary.Article;
import com.zhs.repositroy.primary.ArticleRepositroy;
import com.zhs.repositroy.secondary.Message;
import com.zhs.repositroy.secondary.MessageRepositroy;
import com.zhs.service.ArticleService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/14 10:53
 * @package: com.zhs.service.impl
 * @description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepositroy articleRepositroy;

    @Autowired
    private MessageRepositroy messageRepositroy;

    @Autowired
    private Mapper dozerMapper;

    @Override
    @Transactional
    public void saveArticle(Article article) {
        articleRepositroy.save(article);
        Message message = new Message();
        message.setName("11");
        message.setContent("22");
        messageRepositroy.save(message);
        int a =10/0;
    }

    @Override
    public void deleteArticle(Long id){
        articleRepositroy.deleteById(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleRepositroy.save(article);
    }

    @Override
    public Article getArticle(long id) {
        return articleRepositroy.getOne(id);
    }

    @Override
    public List<Article> getAll() {
        return articleRepositroy.findAll();
    }
}
