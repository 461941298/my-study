package com.yjh.study;

import com.yjh.study.tools.SleepTools;

public class Single {
    private static Single single;

    private Single() {

    }

    private static Single getInstance() {
        if (single == null) {
            synchronized (Single.class) {
                if (single == null) {
                    single = new Single();
                    SleepTools.second(10);
                }
            }
        }
        return single;
    }
    private static Single getSingle(){
        return Inner.getSingle();
    }

    private static class Inner{
        private static Single single = new Single();

        static Single getSingle(){
            return single;
        }
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + "Single{}";
    }

    public static void main(String[] args) {
        new Thread("线程1") {
            @Override
            public void run() {
//                System.out.println(Single.getInstance());
                System.out.println(Single.getSingle());
            }
        }.start();

        SleepTools.second(1);

        new Thread("线程2") {
            @Override
            public void run() {
//                System.out.println(Single.getInstance());
                System.out.println(Single.getSingle());
            }
        }.start();

    }
}
