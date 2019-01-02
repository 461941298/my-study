package com.yjh.study.ch1线程基础;

public class UseThreadLocal {

//    可以理解为 Map<Thread, Integer>
    static ThreadLocal<Integer> threadLocal = new ThreadLocal(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    private static class RunThread implements Runnable{
        int id;

        public RunThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            int a = threadLocal.get() + this.id;
            System.out.println(Thread.currentThread().getName() + " " + a);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 4; i++) {
             new Thread(new RunThread(i)).start();
        }

    }
}
