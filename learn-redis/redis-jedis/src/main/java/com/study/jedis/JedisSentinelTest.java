package com.study.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYP
 * @date 2023/7/6 14:56
 * @describe Jedis哨兵连接redis
 */
public class JedisSentinelTest {
    public static void main(String[] args) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        String sentinelName = "mymaster";
        Set<String> sentinel = new HashSet<>();
        sentinel.add(new HostAndPort("192.168.75.128",26379).toString());
        sentinel.add(new HostAndPort("192.168.75.128",26380).toString());
        sentinel.add(new HostAndPort("192.168.75.128",26381).toString());


        //JedisSentinelPool其实本质跟JedisPool类似，都是与redis主节点建立的连接池
        //JedisSentinelPool并不是说与sentinel建立的连接池，而是通过sentinel发现redis主节点并与其建立连接
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(sentinelName,sentinel,jedisPoolConfig,3000,null);
        Jedis jedis  = null;

        try {

            jedis = jedisSentinelPool.getResource();
            System.out.println(jedis.set("chen", "test"));
            System.out.println(jedis.get("chen"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //注意这里不是关闭连接，在JedisPool模式下，Jedis会被归还给资源池
            if (jedis != null){
                jedis.close();
            }
        }


    }
}
