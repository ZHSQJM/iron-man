package com.zhs.mybatis.annonation;

import java.lang.annotation.*;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 17:35
 * @package: com.zhs.mybatis.annonation
 * @description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface WebLog {

    /**
     * 是否必须打印返回参数 默认是
     * @return
     */
    boolean resultRequired() default  true;

    String value() default "请求";
}
