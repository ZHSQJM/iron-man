package com.zhs.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 10:02
 * @package: com.zhs.listener
 * @description:
 */
@Slf4j
@Component
public class IronListenerApplication2 {

    @EventListener
    public void listener(IronEvent ironEvent){
        log.info(String.format("%s监听到事件源：%s.", IronListenerApplication2.class.getName(), ironEvent.getSource()));
    }
}
