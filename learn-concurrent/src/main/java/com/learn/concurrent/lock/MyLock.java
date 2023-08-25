package com.learn.concurrent.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author ChenYP
 */

public class MyLock extends AbstractQueuedSynchronizer {



    @Override
    protected boolean tryAcquire(int arg) {
        // 尝试加锁
        if (compareAndSetState(0,1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        // 释放锁不需要考虑并发安全，因为只会有一个线程来解锁
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock(){
        acquire(1);
    }

    public void unlock(){
        release(1);
    }

    public boolean tryAcquire(){
        return tryAcquire(1);
    }



}
