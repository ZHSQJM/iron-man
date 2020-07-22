package com.zhs.repositroy.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/14 10:39
 * @package: com.zhs.entity
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false,length = 32)
    private String author;

    private String title;

    private String content;

    private Date createTime;

//    private List<Reader> reader;
}
