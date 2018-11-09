package com.yjh.study.ch4显示锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//    使用显示锁的范示
public class LockDemo {

//    可重入锁，排他锁，可以认为是synchronized的实现，它的构造参数如果为true，则为公平锁。
    private Lock lock = new ReentrantLock();
    private int count = 0;

    public void increament(){
        lock.lock();
        try {
            count++;
        }finally {
//            一定要在finally块中释放锁
            lock.unlock();
        }
    }

}
