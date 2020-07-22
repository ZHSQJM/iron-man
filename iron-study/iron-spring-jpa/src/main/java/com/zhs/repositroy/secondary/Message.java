package com.zhs.repositroy.secondary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.lang.reflect.ParameterizedType;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/14 11:24
 * @package: com.zhs.entity
 * @description:
 */
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private String content;
}
