package com.learn.concurrent.completablefuture.flow;


import com.learn.concurrent.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：结合转化类
 */
public class ThenCombine {
    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> {
            SleepTools.second(2);
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            SleepTools.second(1);
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
    }
}
