package com.yjh.study.ch1线程基础.safeEnd;

public class HasInterruptedException {

    private static class RunThread implements Runnable{

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            while (!thread.isInterrupted()){
                System.out.println(thread.getName() + " is run");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
//                    抛出异常时，catch里要注意中断标志位,不然线程会一直运行
                    thread.interrupt();
                    System.out.println(thread.isInterrupted());
                }finally {
//                    如果线程是守护线程，那么即使发生 InterruptedException 可能catch和finally中的代码都不会执行
                    System.out.println("finally");
                }
            }
            System.out.println(thread.getName() + " flag is " + thread.isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread runThread = new RunThread();
        Thread thread = new Thread(runThread, "RunThread");
//        设置线程为守护线程
//        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
