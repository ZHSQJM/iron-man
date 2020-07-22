package com.zhs.service;

import com.zhs.repositroy.primary.Article;

import java.util.List;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/14 10:51
 * @package: com.zhs.service
 * @description:
 */
public interface ArticleService {

    void saveArticle(Article article);

    void deleteArticle(Long id);

    void updateArticle(Article article);

    Article getArticle(long id);

    List<Article> getAll();
}
