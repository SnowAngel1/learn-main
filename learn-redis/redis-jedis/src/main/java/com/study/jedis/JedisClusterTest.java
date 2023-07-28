package com.study.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYP
 * @date 2023/7/7 10:37
 * @describe Jedis连接redis集群
 */
public class JedisClusterTest {
    public static void main(String[] args) throws IOException {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort("192.168.75.128",8001));
        jedisClusterNodes.add(new HostAndPort("192.168.75.129",8002));
        jedisClusterNodes.add(new HostAndPort("192.168.75.130",8003));
        jedisClusterNodes.add(new HostAndPort("192.168.75.128",8004));
        jedisClusterNodes.add(new HostAndPort("192.168.75.129",8005));
        jedisClusterNodes.add(new HostAndPort("192.168.75.130",8006));

        JedisCluster jedisCluster = null;

        try {
            jedisCluster = new JedisCluster(jedisClusterNodes,6000,5000,10,"root",jedisPoolConfig);
            System.out.println(jedisCluster.set("cluster", "哈哈哈哈哈"));
            System.out.println(jedisCluster.get("cluster"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (jedisCluster != null){
                jedisCluster.close();
            }
        }

    }
}
