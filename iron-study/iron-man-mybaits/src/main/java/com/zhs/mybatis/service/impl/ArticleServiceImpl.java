package com.zhs.mybatis.service.impl;

import com.zhs.mybatis.dao.primary.Article;
import com.zhs.mybatis.dao.primary.ArticleDao;
import com.zhs.mybatis.dao.secondary.Message;
import com.zhs.mybatis.dao.secondary.MessageDao;
import com.zhs.mybatis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/20 17:33
 * @package: com.zhs.mybatis.service.impl
 * @description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private MessageDao messageDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save() {

        Message message = new Message();
        message.setContent("ddd");
        message.setName("aa");
        messageDao.insert(message);
        int a = 1/0;
        Article article = new Article();
        article.setAuthor("周华生");
        article.setCreateTime(new Date());
        article.setTitle("测试");
        article.setContent("ddddd1");
        articleDao.insert(article);
    }
}
