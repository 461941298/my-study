package com.yjh.study.ch2高并发工具类;

import java.util.concurrent.Exchanger;

public class UseExchange {

    private static Exchanger<String> exchanger = new Exchanger<>();

    //    业务线程1
    private static class Thread1 extends Thread {
        @Override
        public void run() {
            String a = "hello";
            try {
                a = exchanger.exchange(a);
                System.out.println("线程1交换后的结果是" + a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //    业务线程2
    private static class Thread2 extends Thread {
        @Override
        public void run() {
            String a = "world";
            try {
                a = exchanger.exchange(a);
                System.out.println("线程2交换后的结果是" + a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }
}
