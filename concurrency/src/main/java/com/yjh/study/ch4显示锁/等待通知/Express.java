package com.yjh.study.ch4显示锁.等待通知;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Express {

    private Long km = 0L;
    private Long oldKm = 0L;
    private Lock lock = new ReentrantLock();
//    一个lock可以对应多个condition
    private Condition condition = lock.newCondition();

    public Long getKm() {
        return km;
    }

    public void setKm(Long km) {
        lock.lock();
        try {
            this.oldKm = this.km;
            this.km = km;
            if(this.km != this.oldKm) {
                condition.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }

    public void listenKm() throws InterruptedException {
        lock.lock();
        try {
            while (this.km == this.oldKm) {
                System.out.println(Thread.currentThread().getName() + " 将被暂停");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " 被通知");
                System.out.println(Thread.currentThread().getName() +" 记录数据库");
            }
        }finally {
            lock.unlock();
        }
    }

}
