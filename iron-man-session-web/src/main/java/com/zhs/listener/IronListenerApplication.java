package com.zhs.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 09:57
 * @package: com.zhs.listener
 * @description:
 */
@Slf4j
@Component
public class IronListenerApplication implements ApplicationListener<IronEvent> {
    @Override
    public void onApplicationEvent(IronEvent event) {
        log.info(String.format("%s监听到事件源：%s.", IronListenerApplication.class.getName(), event.getSource()));
    }
}
