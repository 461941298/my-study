package com.yjh.study.ch2高并发工具类;

import java.util.concurrent.CountDownLatch;

// 演示CountDownLatch用法。有5个初始化线程，6个扣除点
// 扣除完毕后,主线程和业务线程继续自己的工作
public class UseCountDownLetch {

    private static CountDownLatch countDownLatch = new CountDownLatch(6);

    //    初始化线程任务
    private static class InitThread implements Runnable {

        @Override
        public void run() {
            threadPrint("工作中", 2);
            countDownLatch.countDown();
        }
    }

    //    业务线程任务
    private static class BusinessThread implements Runnable {

        @Override
        public void run() {
            try {
                countDownLatch.await();
                threadPrint("工作中", 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        先开启一个线程，完成两个工作（作两次扣除）
        new Thread("FirstInit") {
            @Override
            public void run() {
                threadPrint("完成第一部分工作", 2);
                countDownLatch.countDown();
                threadPrint("完成第二部分工作", 2);
                countDownLatch.countDown();
            }
        }.start();

//        开启4个初始化线程
        for (int i = 0; i < 4; i++) {
            new Thread(new InitThread(), "初始化线程" + i).start();
        }

//        开启业务线程
        new Thread(new BusinessThread(), "业务线程").start();

//        让主线程在所有初始化线程完成之后再工作
        countDownLatch.await();
        threadPrint("工作中", 5);
    }

    private static void threadPrint(String content, int repeat) {
        for (int i = 0; i < repeat; i++) {
            System.out.println("线程:[" + Thread.currentThread().getName() + "] " + content);
        }
    }
}
