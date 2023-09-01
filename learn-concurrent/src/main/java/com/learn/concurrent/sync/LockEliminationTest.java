package com.learn.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ChenYP
 * 锁消除
 */
@Slf4j
public class LockEliminationTest {


    /**
     * 开启锁消除：-XX:+EliminateLocks（JDK8默认开启）
     * 关闭锁消除：-XX:-EliminateLocks
     * @param args
     */
    public static void main(String[] args) {
        LockEliminationTest lockEliminationTest = new LockEliminationTest();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            lockEliminationTest.append("aaa","bbb");
        }
      log.info("执行时间："+(System.currentTimeMillis() - start) + "ms");

    }

    private  void append(String s1,String s2){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(s1).append(s2);
    }
}
