package com.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenYP
 * @date 2023/7/7 14:40
 * @describe
 */

@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private Redisson redisson;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping(value = "/deductStock")
    public String deductStock() {

        String lockKey = "lock:product_101";
        //获取锁对象
        RLock lock = redisson.getLock(lockKey);
        //加分布式锁
        lock.lock();
        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", String.valueOf(realStock));
                logger.info("扣减成功：剩余库存:{}", realStock);
            } else {
                logger.info("扣减失败，库存不足");
            }
        } finally {
            //解锁
            lock.unlock();
        }


        return "end";
    }

    @RequestMapping(value = "/testConnectRedis")
    public String connectRedis(){
        stringRedisTemplate.opsForValue().set("connectTest","chen");
        System.out.println(stringRedisTemplate.opsForValue().get("connectTest"));
        return "";

    }

}
