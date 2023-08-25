package com.learn.concurrent.completableFuture.flow;


import com.learn.concurrent.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：执行操作类
 */
public class ThenRun {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            SleepTools.second(1);
            return "hello";
        }).thenRun(() -> System.out.println("hello world"));
        SleepTools.second(2);
    }
}
