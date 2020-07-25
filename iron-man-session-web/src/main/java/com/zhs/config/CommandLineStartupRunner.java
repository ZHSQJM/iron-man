package com.zhs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 10:05
 * @package: com.zhs.config
 * @description: 应用启动得监听 这里可以做一些事情  比如启动netty
 */
@Slf4j
@Component
public class CommandLineStartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner传入参数：{}", Arrays.toString(args));
    }
}
