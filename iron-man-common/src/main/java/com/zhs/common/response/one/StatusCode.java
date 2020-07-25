package com.zhs.common.response.one;

import lombok.Data;
import lombok.Getter;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 10:56
 * @package: com.zhs.common.response
 * @description: 通用状态吗
 */
@Getter
public enum  StatusCode {

    Success(0,"成功"),
    Fail(-1,"失败"),
    InvalidParams(201,"非法的参数!"),
    InvalidGrantType(202,"非法的授权类型");
    private Integer code;
    private String msg;
    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
