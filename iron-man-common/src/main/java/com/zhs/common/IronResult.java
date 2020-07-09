package com.zhs.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/5 18:32
 * @package: com.zhs.common
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IronResult {

    private String code;

    private String msg;

    private Object data;

    public static  IronResult isOk(){
        IronResult result = new IronResult();
        result.setCode("200");
        result.setData(null);
        result.setMsg("操作成功");
        return result;
    }

    public static  IronResult isOk(Object data){
        IronResult result = new IronResult();
        result.setCode("200");
        result.setData(data);
        result.setMsg("操作成功");
        return result;
    }
    public static  IronResult isOk(Object data,String msg){
        IronResult result = new IronResult();
        result.setCode("200");
        result.setData(data);
        result.setMsg(msg);
        return result;
    }
    public static  IronResult fail(String msg){
        IronResult result = new IronResult();
        result.setCode("500");
        result.setData(null);
        result.setMsg(msg);
        return result;
    }
}
