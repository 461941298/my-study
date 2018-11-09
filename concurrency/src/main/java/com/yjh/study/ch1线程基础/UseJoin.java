package com.yjh.study.ch1线程基础;

public class UseJoin {

    private static class RunThreadA implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class RunThreadB implements Runnable {

        private Thread thread;

        public RunThreadB(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(1000);
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunThreadA(), "A");
        Thread thread2 = new Thread(new RunThreadB(thread1), "B");

        thread2.start();

        thread1.start();
    }
}
