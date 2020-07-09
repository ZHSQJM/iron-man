package com.zhs.entity.blog;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author hszhou
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String content;

    private LocalDateTime createTime;

    private String title;

    private LocalDateTime updateTime;

    private String categoryId;

    private Integer flag;

    private Integer type;

    /**
     * 概要
     */
    private String synopsis;

    private Integer look;


}
