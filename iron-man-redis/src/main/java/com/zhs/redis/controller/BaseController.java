package com.zhs.redis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 16:39
 * @package: com.zhs.redis.controller
 * @description:
 */
@RestController
@RequestMapping("base")
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final String RedisHelloWorldKey = "SpringBootRedis:HelloWorld";


    @RequestMapping(value = "/hello/world/put", method = RequestMethod.POST)
    @ResponseBody
    public Object helloWorldPut(@RequestParam String helloName) {

        try {
            stringRedisTemplate.opsForValue().set(RedisHelloWorldKey, helloName);

        } catch (Exception e) {
            log.info("--hello world get异常信息： ", e.fillInStackTrace());

        }
        return "SUCCESS";
    }

    @RequestMapping(value = "/hello/world/get", method = RequestMethod.GET)
    @ResponseBody
    public Object helloWorldGet() {

        String result = "";
        try {
            result = stringRedisTemplate.opsForValue().get(RedisHelloWorldKey);

        } catch (Exception e) {
            log.info("--hello world get异常信息： ", e.fillInStackTrace());
        }
        return result;
    }
}