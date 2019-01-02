package com.yjh.study.ch5并发容器.bq;

import java.util.concurrent.DelayQueue;

public class GetItem extends Thread {

    private DelayQueue<Item> delayQueue;

    public GetItem(DelayQueue<Item> delayQueue) {
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
//                take是一个阻塞方法，如果没有元素到期就拿不到元素
                Item item = delayQueue.take();
                System.out.println("拿到元素 ：" + item.getData());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
