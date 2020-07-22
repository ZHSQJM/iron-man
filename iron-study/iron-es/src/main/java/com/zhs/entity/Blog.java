package com.zhs.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/13 11:02
 * @package: com.zhs.entity
 * @description:
 */
@Data
public class Blog {

    private String id;

    private String  content;

    private LocalDateTime createTime;

    private String title;


    private LocalDateTime updateTime;


    private String categoryId;

    private int flag;

    private int type;

    private String synopsis;

    private int look;
}
