package com.zhs.dao;

import com.zhs.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/13 10:58
 * @package: com.zhs.dao
 * @description:
 */
@Repository
public class BlogDao {

    @Resource(name = "secondaryTemplate")
    private JdbcTemplate secondaryTemplate;

    @Resource(name = "primaryJdbcTemplate")
    private JdbcTemplate primaryTemplate;

    public void save(Blog blog){
        primaryTemplate.update("INSERT INTO `blog`(`id`, `content`, `create_time`, `title`, `update_time`, `category_id`, `flag`, `type`, `synopsis`, `look`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                blog.getId(),blog.getContent(),LocalDateTime.now(),blog.getTitle(),LocalDateTime.now(),blog.getCategoryId(),
                blog.getFlag(),blog.getType(),blog.getSynopsis(),blog.getLook());
    }

    public void savetwo(Blog blog){
        secondaryTemplate.update("INSERT INTO `blog`(`id`, `content`, `create_time`, `title`, `update_time`, `category_id`, `flag`, `type`, `synopsis`, `look`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                blog.getId(),blog.getContent(),LocalDateTime.now(),blog.getTitle(),LocalDateTime.now(),blog.getCategoryId(),
                blog.getFlag(),blog.getType(),blog.getSynopsis(),blog.getLook());
    }


    public void deleteById(String id){
        primaryTemplate.update("DELETE FROM blog WHERE id =?",id);
    }


    public Blog findById(String id){
       return primaryTemplate.queryForObject("SELECT* FROM blog WHERE id = ?",new Object[]{id},new BeanPropertyRowMapper<>(Blog.class));
    }

    public List<Blog> findAll(){
        return primaryTemplate.query("SELECT* FROM blog ",new BeanPropertyRowMapper<>(Blog.class));
    }
}
