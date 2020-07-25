package com.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhs.plus.dao.ArticleMapper;
import com.zhs.plus.dao.SkuInfoMapper;
import com.zhs.plus.entity.Article;
import com.zhs.plus.entity.SkuInfo;
import com.zhs.plus.service.ArticleService;
import com.zhs.plus.service.ISkuInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 10:32
 * @package: com.test
 * @description:
 */
@SpringBootTest(classes={com.zhs.plus.PlusApplication.class})
@RunWith(SpringRunner.class)
public class MainTest {

    @Autowired
    private ISkuInfoService skuInfoService;

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;


    @Test
    public void test01(){
        final int save = skuInfoService.save();
        System.out.println(save);
    }

    @Test
    public void test02(){


        final List<SkuInfo> list =
                skuInfoService.query("机");

        System.out.println(list);
    }

    @Test
    public void test03(){

        LambdaQueryWrapper<SkuInfo> query = new LambdaQueryWrapper<>();
        query.like(SkuInfo::getCategory,"机");
        Page<SkuInfo> page = new Page<>(1,10);   //查询第1页，每页10条数据
        skuInfoMapper.selectPage(page,query);   //page分页信息，query查询条件
    }

    @Test
    public void test04(){

        Article article = new Article();
        article.setAuthor("周华生");
        article.setContent("这世界哦每天拍卖会");
        article.setCreateTime(LocalDateTime.now());
        article.setTitle("标题");
        articleMapper.insert(article);
    }

    @Test
    public void test05(){

        articleService.save();
    }
}
