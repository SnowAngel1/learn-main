package com.learn.concurrent.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYP
 * 自己实现ThreadLocal
 */

public class MyThreadLocal<T> {
    private Map<Thread,T> map = new HashMap<>();

    public synchronized T get(){
        return map.get(Thread.currentThread());
    }

    public synchronized void set(T t){
        map.put(Thread.currentThread(),t);
    }



}
