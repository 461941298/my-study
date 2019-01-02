package com.yjh.study.ch1线程基础;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewThread {

//    创建线程有三种方式
//    1. 继承 Thread 类
//    2. 实现 Runnable 接口
//    3. 实现 Callable 接口

    private static class RunThread implements  Runnable{

        @Override
        public void run() {
            System.out.println("run thread");
        }
    }

    private static class CallThread implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("call thread");
            return "call";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(new RunThread());
        t1.start();

        CallThread ct1 = new CallThread();
        FutureTask<String> ft1 = new FutureTask<>(ct1);
        Thread t2 = new Thread(ft1);
        t2.start();
        System.out.println(ft1.get());

    }
}
