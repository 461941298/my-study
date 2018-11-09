package com.yjh.study.ch2高并发工具类.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class DBPoolSemaphore {

    private static Integer PoolSize = 10;
    private static LinkedList<Connection> Pool;
//    useful 表示可用连接， useless 表示已用连接
    private Semaphore useful, useless;

    public DBPoolSemaphore() {
        this.useful = new Semaphore(PoolSize);
        this.useless = new Semaphore(0);
    }

    static {
        Pool = new LinkedList<>();
        for (int i = 0; i < PoolSize; i++) {
            Pool.add(SqlConnection.fetchConnection());
        }
    }

//    从池子中拿连接
    public Connection takeConnect() throws InterruptedException {
        System.out.println("当线可用连接数 " + useful.availablePermits() + "; 不可用连接数 " + useless.availablePermits()
                + "; 有 " + useful.getQueueLength() + " 在等待连接");

//        申请证书, 可用连接-1。如果可用连接数0，那么线程就会阻塞
        useful.acquire();
        Connection connection;
        synchronized (Pool){
            connection = Pool.removeFirst();
        }
//        不可用(已用)连接+1
        useless.release();
        return connection;
    }

    public void returnConnect(Connection connection) throws InterruptedException {
        if(null != connection){
            useless.acquire();
            synchronized (Pool){
                Pool.addLast(connection);
            }
            useful.release();
        }
    }
}
