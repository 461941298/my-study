package com.yjh.study.ch5并发容器.bq;

import com.yjh.study.tools.SleepTools;

import java.util.concurrent.DelayQueue;

public class Test {

    public static void main(String[] args) {
        DelayQueue<Item> delayQueue = new DelayQueue();
        new PutItem(delayQueue).start();
        new GetItem(delayQueue).start();

        for (int i = 0; i < 20; i++) {
            SleepTools.ms(500);
            System.out.println("过了" + (i * 500));
        }

    }
}
