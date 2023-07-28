package com.lock;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author ChenYP
 * @date 2023/7/7 16:53
 * @describe
 */
@Component
public class RedissonConfig {
    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
        return (Redisson)Redisson.create(config);
    }
}
