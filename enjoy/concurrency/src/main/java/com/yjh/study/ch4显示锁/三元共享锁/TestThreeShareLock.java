package com.yjh.study.ch4显示锁.三元共享锁;

import com.yjh.study.tools.SleepTools;

public class TestThreeShareLock {

    private static ThreeShareLock lock = new ThreeShareLock(3);

    //    工作线程
    static class WorkThread extends Thread {
        public WorkThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
                SleepTools.second(2);
            } finally {
                lock.unlock();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            if (0 == i % 5) {
                SleepTools.second(1);
            }
            new WorkThread("线程" + i).start();
        }
    }
}
