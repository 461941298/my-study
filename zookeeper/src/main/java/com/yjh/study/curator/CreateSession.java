package com.yjh.study.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateSession {

    private final static String CONNECTION_STR = "192.168.1.190:2181";

    public static void main(String[] args) throws InterruptedException {
        // 首次连接超时时间为1000ms， 尝试三次连接， 每次连接超时时间叠加
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .newClient(CONNECTION_STR, 5000, 5000,
                        new ExponentialBackoffRetry(1000, 3));
        // 启动连接
        curatorFramework.start();

        // fluent风格
//        CuratorFramework curatorFramework1 = CuratorFrameworkFactory.builder()
//                .connectString(CONNECTION_STR)
//                .sessionTimeoutMs(5000)
//                .connectionTimeoutMs(5000)
//                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
//                .build();
//        curatorFramework1.start();

        // 创建节点
//        try {
//            curatorFramework.create().creatingParentContainersIfNeeded()
//                    .withMode(CreateMode.PERSISTENT)
//                    .forPath("/curator/curator1/curator11", "123".getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // 删除节点
//        try {
//            curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // 查询
//        try {
//            Stat stat = new Stat();
//            byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/enjoy1");
//            System.out.println(new String(bytes));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // 更新
//        Stat stat1 = null;
//        try {
//            stat1 = curatorFramework.setData().forPath("/enjoy1", "qqq".getBytes());
//            System.out.println(stat1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        // 异步操作
//        ExecutorService service = Executors.newFixedThreadPool(1);
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//
//        try {
//            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
//                    .inBackground(new BackgroundCallback() {
//                        @Override
//                        public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
//                            System.out.println(Thread.currentThread().getName()
//                                    + " resultCode： " + curatorEvent.getResultCode()
//                                    + " -> " + curatorEvent.getType());
//                            countDownLatch.countDown();
//                        }
//                    }, service).forPath("/enjoy", "yjh".getBytes());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        countDownLatch.await();
//        service.shutdown();


        //事务操作，curator独有的
        Collection<CuratorTransactionResult> results = null;
        try {
            results = curatorFramework.inTransaction()
                    .create().forPath("/demo2", "111".getBytes())
                    .and()
                    .setData().forPath("/demo3", "112".getBytes())
                    .and()
                    .commit();
            for (CuratorTransactionResult result : results) {
                System.out.println(result.getForPath() + "->" + result.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
