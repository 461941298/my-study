package com.yjh.study.ch5并发容器.bq;


import java.util.concurrent.DelayQueue;

public class PutItem extends Thread {

    private DelayQueue<Item> delayQueue;

    public PutItem(DelayQueue<Item> delayQueue) {
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        Order order1 = new Order("yjh123");
        long t1 = 5000L; //设置5钞后过期
        Item<Order> item1 = new Item(t1, order1);
        delayQueue.offer(item1);
        System.out.println("添加订单1, 5秒后过期");

        Order order2 = new Order("mm123");
        long t2 = 8000L; //设置5钞后过期
        Item<Order> item2 = new Item(t2, order2);
        delayQueue.offer(item2);
        System.out.println("添加订单2, 8秒后过期");
    }
}
