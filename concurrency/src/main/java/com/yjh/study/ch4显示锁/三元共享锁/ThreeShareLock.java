package com.yjh.study.ch4显示锁.三元共享锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ThreeShareLock implements Lock {

    private Sync sync;
    public ThreeShareLock(int share) {
        this.sync = new Sync(share);
    }

    static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            if(0>=count){
                throw new RuntimeException("参数错误");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (;;){
                int current = getState();
                int newCount = current - arg;
                if(newCount<0||compareAndSetState(current, newCount )){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;){
                int current = getState();
                int newCurrent = current + arg;
                if(compareAndSetState(current, newCurrent)){
                    return true;
                }
            }
        }
    }


    @Override
    public void lock() {
        sync.acquireShared(1);
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
        return sync.new ConditionObject();
    }
}
