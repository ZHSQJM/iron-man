package com.zhs.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 09:13
 * @package: com.zhs.listener
 * @description:
 */
@WebListener
@Slf4j
public class IronListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
          log.info("request监听器:销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        log.info("request监听器，可以在这里记录访问次数");
    }
}
