package com.zhs.entity.file;

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
 * @since 2020-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class IronFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 相对路径
     */
    private String path;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 大小
     */
    private Integer size;

    /**
     * 类型 图片
     */
    private String type;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

    /**
     * 已上传分片
     */
    private Integer shardIndex;

    /**
     * 分片大小
     */
    private Integer shardSize;

    /**
     * 分片总数
     */
    private Integer shardTotal;

    /**
     * 文件表示
     */
    private String key1;


}
