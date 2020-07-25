package com.zhs.common.response.one;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 10:53
 * @package: com.zhs.common.response
 * @description: 统一消息响应模型
 */
@Data
@AllArgsConstructor
public class BaseResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    public  BaseResponse(Integer code,String msg){
        this.code =code;
        this.msg = msg;
    }
    public BaseResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }
}
