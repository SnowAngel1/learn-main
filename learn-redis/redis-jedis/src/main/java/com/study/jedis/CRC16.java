package com.study.jedis;

import redis.clients.util.JedisClusterCRC16;

/**
 * @author ChenYP
 * @date 2023/7/7 13:11
 * @describe
 */
public class CRC16 {
    public static void main(String[] args) {

        String str = "name1";
        System.out.println(JedisClusterCRC16.getCRC16(str) % 16384);
        System.out.println(JedisClusterCRC16.getCRC16(str) & (16384 - 1) );
    }
}
