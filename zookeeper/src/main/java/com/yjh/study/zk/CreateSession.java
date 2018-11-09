package com.yjh.study.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class CreateSession {

    // 如果是集群，str用','分隔
    private final static String CONNECTION_STR = "192.168.1.190:2181";
    private final static CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        ZooKeeper zooKeeper = new ZooKeeper(CONNECTION_STR, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                // 如果已经获得了synConnected
                if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                    COUNT_DOWN_LATCH.countDown();
                }
                // 如果数据发生变化
                if (Event.EventType.NodeDataChanged == watchedEvent.getType()) {
                    // 修改的路径
                    String path = watchedEvent.getPath();
                    System.out.println("节点发生了变化，路径是 " + path);

                }
            }
        });

        COUNT_DOWN_LATCH.await();
        System.out.println(zooKeeper.getState());

        // 创建一个节点
        // 节点的路径， 节点值， ACL权限， 节点类型
        zooKeeper.create("/enjoy1", "enjoy".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 获取数据
//        Stat stat = new Stat();
//        byte[] data = zooKeeper.getData("/enjoy1", true, stat);
//        System.out.println(new String(data));
//        System.out.println(stat);



        // 修改数据
        // true表示将watch激活，修改数据后可以被通知
        zooKeeper.getData("/enjoy1", true, null);
        // (-1表示不做版本控制)
        zooKeeper.setData("/enjoy1", "yjh2".getBytes(), -1);

        // 删除节点
//        zooKeeper.delete("/enjoy1", -1);

        // 用客户端手动在 /enjoy1 下添加了三个节点 a,b,c 后
//        List<String> children = zooKeeper.getChildren("/enjoy1", true);
//        System.out.println(children);

        // 当数据或节点的数据变更后通知客户端，但是watch机制是一次性的

    }
}
