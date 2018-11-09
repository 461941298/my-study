package com.yjh.study.ch2高并发工具类;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CyclicBarrier;

// 演示CyclicBarrier的使用
// 在一组线程都达到屏障时，屏障才将所有线程放行
public class UseCyclicBarrier {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new BarrierAction());
    private static ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

    //    屏障打开时任务
    private static class BarrierAction implements Runnable {

        @Override
        public void run() {
            System.out.println("打印 map 开始");
            for (String key : map.keySet()) {
                System.out.print(key + "=" + map.get(key) + " ");
            }
            System.out.println("\n打印 map 结束");
        }
    }

    //    业务线程
    private static class BusinessThread implements Runnable {

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            map.put(thread.getName(), new Random().nextInt(10));
            System.out.println(thread.getName() + " 进行第一部分工作了...");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(thread.getName() + " 进行第二部分工作了...");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new BusinessThread(), "业务线程" + i).start();
        }
    }

}
