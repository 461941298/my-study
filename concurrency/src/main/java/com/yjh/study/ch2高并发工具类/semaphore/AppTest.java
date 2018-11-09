package com.yjh.study.ch2高并发工具类.semaphore;

import java.sql.Connection;
import java.util.Random;

public class AppTest {

    private static DBPoolSemaphore dbPool = new DBPoolSemaphore();

//    业务线程
    private static class BusinessThread extends Thread{
    @Override
    public void run() {
//        让每个线程持有连接的时间不一样
        Random r = new Random();
        long start = System.currentTimeMillis();
        try {
            Connection connection = dbPool.takeConnect();
            System.out.println("Thread_" + Thread.currentThread().getId() +
                    " 获取数据库连接共耗时 " +(System.currentTimeMillis()-start) + "ms");

//            模拟业务操作，线程持有连接查询数据
            Thread.sleep(100+r.nextInt(100));
            System.out.println("查询数据完成， 归还连接");
            dbPool.returnConnect(connection);
        }catch (InterruptedException e){

        }
    }
}

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            Thread thread = new BusinessThread();
            thread.start();
        }
    }

}
