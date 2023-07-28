package com.redisboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenYP
 * @date 2023/7/6 15:20
 * @describe
 */
@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/sentinelTest")
    public void testSentinel(){
        int i = 1;
        while (true){
            try {
                String key = "chen" + i;
                stringRedisTemplate.opsForValue().set(key,i+"");
                logger.info("设置key:{}",key);
                i++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("错误",e);
            }
        }
    }
}
