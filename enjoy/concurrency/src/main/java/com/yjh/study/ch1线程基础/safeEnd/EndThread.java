package com.yjh.study.ch1线程基础.safeEnd;

public class EndThread {

    private static class RunThread implements Runnable{

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            while (!thread.isInterrupted()){
                System.out.println(thread.getName() + " is run");
            }
            System.out.println(thread.getName() + " flag is " + thread.isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread runThread = new RunThread();
        Thread thread = new Thread(runThread, "RunThread");
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
