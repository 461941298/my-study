package com.yjh.study.ch4显示锁.aqs;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SelfLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int arg) {
//            尝试获得锁，如果可以将state从0设置为1表示，获得锁成功，否则失败。
            if(compareAndSetState(0, 1)){
//                获得锁后设置锁的拥有线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
//            如果锁的状态是0表示锁没有被占用
            if(0 == getState()){
                throw new UnsupportedOperationException();
            }
//            设置锁的拥有线程为null
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

    }

    private static final  Sync SYNC = new Sync();
    @Override
    public void lock() {
        SYNC.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        SYNC.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return SYNC.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return SYNC.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        SYNC.release(1);
    }

    @Override
    public Condition newCondition() {
        return SYNC.new ConditionObject();
    }
}
