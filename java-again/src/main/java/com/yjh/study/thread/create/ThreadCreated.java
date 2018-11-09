package com.yjh.study.thread.create;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.*;

public class ThreadCreated {

    @Test
    public void test1() {
//        使用实现了callable接口的线程
        CallableThread ct1 = new CallableThread("1");
        FutureTask<Integer> ft1 = new FutureTask<>(ct1);
        Thread t1 = new Thread(ft1);
        t1.start();

        CallableThread ct2 = new CallableThread("2");
        FutureTask<Integer> ft2 = new FutureTask<>(ct2);
        Thread t2 = new Thread(ft2);
        t2.start();

        try {
            int sum = ft1.get() + ft2.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class CallableThread implements Callable {

    private String name;

    public CallableThread(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        int a = new Random().nextInt(10);
        System.out.println("线程: " + this.name + " int = " + a);
        return a;
    }
}
