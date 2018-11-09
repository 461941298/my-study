package com.yjh.study.thread;

import org.junit.Test;

public class KeyTest {


    /**
     * 测试无同步措施
     */
    @Test
    public void test1() throws InterruptedException {
        AddClass addClass = new AddClass();
        for (int i = 0; i < 10; i++) {
            new Thread(addClass, "线程" + i + " : ").start();
        }

        Thread.sleep(5000);

        assert addClass.getNum() == 10000;
    }

    /**
     * 测试volatile
     */
    @Test
    public void test2() throws InterruptedException {

        AddClass2 addClass = new AddClass2();
        for (int i = 0; i < 10; i++) {
            new Thread(addClass, "线程" + i + " : ").start();
        }

        Thread.sleep(5000);

        assert addClass.getNum() == 10000;

    }
}

class AddClass implements Runnable {

    private int num = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            num = num + 1;
            System.out.println(Thread.currentThread().getName() + num);
        }
    }

    public int getNum() {
        return num;
    }

}

class AddClass2 implements Runnable {

    private volatile int num = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            num = num + 1;
            System.out.println(Thread.currentThread().getName() + num);
        }
    }

    public int getNum() {
        return num;
    }
}