package com.learn.concurrent.sync;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author ChenYP
 * synchronized 延时偏向锁hotspot 虚拟机在启动后有个4s的延时才会对新创建的对象开启偏向锁模式,如果想要看偏向锁需要将线程睡眠5s后在查看
 * JVM开启延迟偏向是因为，JVM在启动时也会有它内部的线程存在竞争。
 * 轻量级锁：在两个线程交替执行时，偏向锁已经偏向了某个线程，再有线程来获取锁，会进行偏向锁撤销，升级为轻量级锁
 */
@Slf4j
public class LockEscalationDemo {


    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        Object o = new Object();

        new Thread(()->{
            // log.info( Thread.currentThread().getName() + "开始执行。。。" + ClassLayout.parseInstance(o).toPrintable());
            synchronized (o){
                log.info( Thread.currentThread().getName() + "获取锁执行。。。" + ClassLayout.parseInstance(o).toPrintable());
            }
            log.info( Thread.currentThread().getName() + "释放锁。。。" + ClassLayout.parseInstance(o).toPrintable());
        },"Thread-1").start();

        new Thread(()->{
            // log.info( Thread.currentThread().getName() + "开始执行。。。" + ClassLayout.parseInstance(o).toPrintable());
            synchronized (o){
                log.info( Thread.currentThread().getName() + "获取锁执行。。。" + ClassLayout.parseInstance(o).toPrintable());
            }
            log.info( Thread.currentThread().getName() + "执行中。。。" + ClassLayout.parseInstance(o).toPrintable());
        },"Thread-2").start();

        Thread.sleep(1);

    }
}
