package com.learn.concurrent.jmm;

/**
 * @author ChenYP
 * 指令重排序，双重检测
 */

public class Singleton {
    private volatile   static Singleton singleton;

    public Singleton() {
        int a = 0;
        int c = a + 1;
        int b = 1;
    }

    /**
     * 双从检测锁定，（Double-check-Locking） 实现单例对象的延时初始化
     * 为什么在创建单例对象双重检测时要加上volatile修饰？
     *      创建对象顺序是，1、先分配对象内存空间  2、初始化对象  3、设置instance指向刚刚分配的内存地址
     *      这样创建很有可能发生指令重排序，顺序成为（1、3、2），一个线程进入获取锁正在执行，另一个线程也来了，并且判断到singleton有值了（因为指令重排序导致这会内存地址已经有指向了，但是这会可能还没有初始化对象，其他线程后边就使用这个对象这是错误的）
     *      所以通过以上描述，需要禁止指令重排序
     * @return
     */
    public static Singleton  getSingleton(){
        // 多个线程判断singleton为空
        if (singleton == null){
            // 只会有一个线程进入，其他线程阻塞，当获取锁的线程创建好对象后，其他线程会被唤醒，如果synchronized内不在判断是否为空，其他线程又会去创建对象
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
