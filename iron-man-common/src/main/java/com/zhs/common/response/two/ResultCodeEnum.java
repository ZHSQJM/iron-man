package com.zhs.common.response.two;

import lombok.Getter;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 14:02
 * @package: com.zhs.common.response.two
 * @description:
 */
@Getter
public enum  ResultCodeEnum {

    SUCCESS(true,20000,"成功"),
    UNKNOWN_ERROR(false,20001,"未知错误"),
    PARAM_ERROR(false,20002,"参数错误"),
            ;

    // 响应是否成功
    private Boolean success;
    // 响应状态码
    private Integer code;
    // 响应信息
    private String message;

    ResultCodeEnum(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
