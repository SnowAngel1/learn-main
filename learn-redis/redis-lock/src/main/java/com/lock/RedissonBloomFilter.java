package com.lock;


import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author ChenYP
 * @date 2023/7/10 16:27
 * @describe Redisson 布隆过滤器
 */
public class RedissonBloomFilter {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        //构造Redisson
        RedissonClient redissonClient = Redisson.create(config);

        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("nameList");
        //初始化布隆过滤器，预计元素为1000000000L，误差率为3%，根据这两个参数会计算出底层的bit数组大小
        bloomFilter.tryInit(90000000L,0.111);
        //将chen、zhang新增到布隆过滤器中
        bloomFilter.add("chen");
        bloomFilter.add("zhang");

        //判断下面号码是否在布隆过滤器中
        System.out.println(bloomFilter.contains("chen1"));
        System.out.println(bloomFilter.contains("chen"));
        System.out.println(bloomFilter.contains("zhang"));

    }
}
