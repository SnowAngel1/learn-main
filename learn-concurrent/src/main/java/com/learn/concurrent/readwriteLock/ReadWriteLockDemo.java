package com.learn.concurrent.readwriteLock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class ReadWriteLockDemo implements ReadWriteLock {

    private final Lock readLock = new ReadLock();


    private final Lock writeLock = new WriteLock();


    @Override
    public Lock readLock() {
        return readLock;
    }

    @Override
    public Lock writeLock() {
        return writeLock;
    }

    private final static Sync sync = new Sync();


    public static class ReadLock implements Lock {
        @Override
        public void lock() {
            sync.tryAcquireShared(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            sync.releaseShared(1);
        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }

    public static class WriteLock implements Lock {
        @Override
        public void lock() {
            sync.tryAcquire(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            sync.tryRelease(1);
        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }

    static class Sync extends AbstractQueuedSynchronizer {

        private int reader;

        private int writer;


        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            Thread thread = Thread.currentThread();

            if (reader > 0 && getExclusiveOwnerThread() != thread) {
                // 当前有读锁且不是当前线程，不能获取写锁，读写互斥
                return false;
            } else if (writer == 0) {
                // 无读锁和写锁，尝试获取读锁
                if (compareAndSetState(state, state + arg)) {
                    setExclusiveOwnerThread(thread);
                    writer++;
                    return true;
                }
                return false;
            } else if (getExclusiveOwnerThread() == thread) {
                //当前线程持有写锁，增加锁的计数器
                setState(state + arg);
                return true;
            }else {
                // 当前线程或者其他线程持有写锁，不能获取锁
                return false;
            }
        }

        @Override
        protected boolean tryRelease(int arg) {
            int state = getState();
            if (getExclusiveOwnerThread() != Thread.currentThread()){
                // 当前线程不持有锁，不能释放写锁
                throw new IllegalMonitorStateException();
            }
            boolean free= false;
            if (writer == 1){
            //     写锁计数器减一，释放锁
                writer--;
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(state-arg);
            return free;
        }

        @Override
        protected int tryAcquireShared(int arg) {
            int state = getState();
            // 当前线程
            Thread thread = Thread.currentThread();
            if (writer > 0 && getExclusiveOwnerThread() != thread){
            //     当前存在写锁，并且不是当前线程持有，不能获取读锁
                return -1;
            }else if(reader == 0){
                // 无锁状态，尝试获取锁
                if (compareAndSetState(state,state + arg)){
                    reader++;
                    return 1;
                }
            }else if(getExclusiveOwnerThread() == thread){
            //     读锁时当前线程持有，可以获取读锁
                if (compareAndSetState(state,state + arg)){
                    reader++;
                    return 1;
                }
            }else {
            //     已经有读锁，可以获取读锁
                if (compareAndSetState(state,state + arg)){
                    reader++;
                    return 1;
                }
            }
            // 尝试获取锁失败
            return -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            int state = getState();
            if (reader > 0){
                reader--;
                setState(state - arg);
                return true;
            }
            // 读锁计数器已经为0了，不能在释放锁了
            throw new IllegalMonitorStateException();
        }
    }
}



