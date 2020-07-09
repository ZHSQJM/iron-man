package com.zhs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/5 21:59
 * @package: com.zhs.dto
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    String shard;
    String name;
    String suffix;
    Integer size;
    Integer shardIndex;
    Integer shardSize;
    Integer shardTotal;
    String type;
    String key1;
}
