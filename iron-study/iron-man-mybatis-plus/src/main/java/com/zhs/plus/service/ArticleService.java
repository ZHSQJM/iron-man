package com.zhs.plus.service;

import com.zhs.plus.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhs
 * @since 2020-07-24
 */
public interface ArticleService extends IService<Article> {



    void  save();
}
