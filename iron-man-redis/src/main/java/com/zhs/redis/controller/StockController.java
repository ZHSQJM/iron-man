package com.zhs.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/24 13:45
 * @package: com.zhs.redis.controller
 * @description:
 */

@RestController
@RequestMapping
@Slf4j
public class StockController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Autowired
//    private Redisson redisson;

    /**
     * incr
     * @return
     */
    @GetMapping("/reduce_stock")
    public   String reduceStock(){

        String lockKey = "lock";
        String clientId =UUID.randomUUID().toString()+"-"+Thread.currentThread().getId();

        /**
         * 注意只有2.1.X版本以上才有的方法
         */
        final Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey,clientId,30,TimeUnit.SECONDS);

        /**
         *    设置超时时间 避免死锁 分开不是原子操作
         */
       // final Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId);
      // stringRedisTemplate.expire(lockKey,10,TimeUnit.SECONDS);
        if(!result){
            return "fail";
        }
        try{
            Integer stock =Integer.parseInt( stringRedisTemplate.opsForValue().get("stock"));
            if(stock>0){
                stock = stock-1;
                stringRedisTemplate.opsForValue().set("stock",stock+"");
                log.info("扣减库存成功，库存stock:"+stock);
            }else{
                log.error("扣减失败，库存不足");
            }

            /**
             * 当锁的有效时间再30秒的时候
             * 但是执行的业务逻辑时间超过30秒  当A线程执行的的业务时间是35秒的时候  A线程继续执行，但是锁已经释放，此时B线程可以获取到锁了
             * 1：当A线程再运行5秒后  释放锁  此时释放的锁是B的锁
             *  再加锁的时候 设置值未当前线程的线程数  当释放锁的时候 判断锁是不是 当前线程的 如果是 那么就释放，如果不是  就不能释放
             * 2:相同的5秒钟内 存在着A与B2个线程同时运行
             * 动态的设置时间，判断当前线程是否还持有锁，(后台启动一个线程  每隔10秒判断是否还尺有锁如果存在  那么久加时间)
             */
        }finally {
            //释放锁
            if(clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))){
                stringRedisTemplate.delete(lockKey);
            }

        }
        return "end";
    }

//    @GetMapping("/red_stock")
//    public   String ressienStock(){
//
//        String lockKey = "lock";
//         RLock lock = redisson.getLock(lockKey);
//        lock.lock(30,TimeUnit.SECONDS);
//        try{
//            Integer stock =Integer.parseInt( stringRedisTemplate.opsForValue().get("stock"));
//            if(stock>0){
//                stock = stock-1;
//                stringRedisTemplate.opsForValue().set("stock",stock+"");
//                log.info("扣减库存成功，库存stock:"+stock);
//            }else{
//                log.error("扣减失败，库存不足");
//            }
//        }finally {
//            lock.unlock();
//        }
//        return "end";
//    }
}
