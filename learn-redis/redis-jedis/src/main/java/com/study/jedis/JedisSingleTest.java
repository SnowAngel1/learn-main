package com.study.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;

/**
 * @author ChenYP
 * @date 2023/7/6 14:07
 * @describe Jedis连接单机redis
 */
public class JedisSingleTest {


    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.75.128",6379,3000,null);

        Jedis jedis = null;


        try {
            //从redis连接池中拿出一个链接执行命令
            jedis = jedisPool.getResource();
          /*  //普通操作
            System.out.println(jedis.set("single","chen"));
            System.out.println(jedis.get("single"));

            //管道操作（批量执行一批命令）
            Pipeline pipelined = jedis.pipelined();
            for (int i = 0; i < 10; i++) {
                pipelined.set("chen"+i,"chen");
                pipelined.incr("pipelined");
                //模拟管道报错（注意：管道操作不支持事务）
                pipelined.setbit("chen",-1,true);
            }
            List<Object> result = pipelined.syncAndReturnAll();
            System.out.println(result);*/


            //********lua脚本示例*************
            //模拟一个商品减库存的原子操作
            //lua脚本命令的执行方式：redis-cli --eval /tmp/test.lua ,10
            jedis.set("product_stock_10016","15"); //初始化商品10016的库存
            String script = "local count = redis.call('get',KEYS[1]) " +
                    "local a = tonumber(count) " +
                    "local b = tonumber(ARGV[1]) " +
                    "if a >= b then " +
                    "redis.call('set',KEYS[1],a-b) " +
                    "return 1 " +
                    "end " +
                    "return 0 ";

            Object obj = jedis.eval(script, Arrays.asList("product_stock_10016"), Arrays.asList("10"));
            System.out.println(obj);







        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //注意这里不是管理连接，在JedisPool模式下，Jedis会被归还给连接池
            if (jedis != null){
                jedis.close();
            }
        }


    }
}
