package com.zhs.plus.service.impl;

import com.zhs.plus.dao.SkuInfoMapper;
import com.zhs.plus.entity.Article;
import com.zhs.plus.dao.ArticleMapper;
import com.zhs.plus.entity.SkuInfo;
import com.zhs.plus.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhs.plus.service.ISkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhs
 * @since 2020-07-24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Autowired
    private ISkuInfoService skuInfoService;


    /**
     * 默认的是Propagation.REQURIED 如果当前存在事务，那么就合并事务
     *  Propagation.SUPPORTS 如果当前有事务，那么就加入事务  如果没有  那么就以非事务的方式
     *  Propagation.NOT_SUPPORTED：如果当前有事务 那么就暂停当前事务 以非事务方式运行
     *  Propagation.MANDATROY :使用当前事务  如果没有事务 就抛异常
     *  Propagation.NEVER:以非事务的方式运行 如果存在事务 就抛出异常
     *  Propagation.REQURIED_NEW:暂停当前事务，创建新的事务
     *
     */

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    public void save() {
        skuInfoService.save();
        Article article = new Article();
        article.setTitle("机器");
        super.save(article);
    }
}
