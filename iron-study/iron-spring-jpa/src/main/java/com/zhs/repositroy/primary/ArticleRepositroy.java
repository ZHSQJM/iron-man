package com.zhs.repositroy.primary;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/14 10:42
 * @package: repositroy
 * @description:
 */
public interface ArticleRepositroy extends JpaRepository<Article,Long> {
}
