package com.yjh.study.ch3CAS;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCount {

    private static Integer count = 0;
    private static AtomicInteger atomicCount = new AtomicInteger(count);
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(50);

    static class CountThread extends Thread {

        @Override
        public void run() {
            boolean flag = true;
            Integer count;
            while (flag) {
                count = atomicCount.get();
                flag = atomicCount.compareAndSet(count, ++count);
//                如果设置成功就可以跳出循环，否则自旋
                flag = flag ? false : true;
            }

            try {
//                初始值是50，因为main中开了50个线程，保证这些线程跑完后，再打印信息。
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        for (int i = 0; i < 50; i++) {
            new CountThread().start();
        }

        System.out.println(count);
        System.out.println(atomicCount.get());

    }
}
