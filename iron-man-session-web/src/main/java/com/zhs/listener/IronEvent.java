package com.zhs.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 10:00
 * @package: com.zhs.listener
 * @description: 自定义时间代码
 */
@SuppressWarnings("serial")
public class IronEvent extends ApplicationEvent {
    public IronEvent(Object source) {
        super(source);
    }
}
