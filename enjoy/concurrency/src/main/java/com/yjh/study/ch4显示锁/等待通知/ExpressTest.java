package com.yjh.study.ch4显示锁.等待通知;

public class ExpressTest {
    private static Express express = new Express();

    private static class ListerKm extends Thread{
        public ListerKm(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                express.listenKm();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            new ListerKm("线程"+i).start();
        }
        Thread.sleep(3000);

        express.setKm(1L);

    }
}
