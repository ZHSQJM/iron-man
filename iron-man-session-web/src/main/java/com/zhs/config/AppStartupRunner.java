package com.zhs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 10:06
 * @package: com.zhs.config
 * @description: 应用启动得监听 这里可以做一些事情  比如启动netty
 */
@Slf4j
@Component
public class AppStartupRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationRunner参数: {}", args.getOptionNames());
    }
}
