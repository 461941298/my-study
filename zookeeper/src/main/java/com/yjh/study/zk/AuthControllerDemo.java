package com.yjh.study.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AuthControllerDemo implements Watcher {

    private final static String CONNECT_STRING = "192.168.1.190:2181";
    private final static CountDownLatch COUNT_DOWN_LATCH1 = new CountDownLatch(1);
    private final static CountDownLatch COUNT_DOWN_LATCH2 = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;
    private static ZooKeeper zooKeeper1;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper(CONNECT_STRING, 5000, new AuthControllerDemo());
        COUNT_DOWN_LATCH1.await();

        // 权限有：读，写，创建，删除 和 admin
        // 这里将所有的权限给了 用户名为 root， 密码为 root 的用户
        ACL acl1 = new ACL(ZooDefs.Perms.ALL, new Id("digest", DigestAuthenticationProvider.generateDigest("root:root")));
        // 将创建权限给了这个 ip
        ACL acl2 = new ACL(ZooDefs.Perms.CREATE, new Id("ip", "192.168.1.180"));

        List<ACL> acls = new ArrayList<>();
        acls.add(acl1);
        acls.add(acl2);
        zooKeeper.create("/auth1", "123".getBytes(), acls, CreateMode.PERSISTENT);
        zooKeeper.addAuthInfo("digest", "root:root".getBytes());
        zooKeeper.create("/auth1/auth1-1", "123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        zooKeeper1 = new ZooKeeper(CONNECT_STRING, 5000, new AuthControllerDemo());
        COUNT_DOWN_LATCH2.await();
        zooKeeper1.addAuthInfo("digest", "root:root".getBytes());
        zooKeeper1.delete("/auth1/auth1-1", -1);

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                COUNT_DOWN_LATCH1.countDown();
                System.out.println(watchedEvent.getState() + " " + watchedEvent.getType());
            }
        }
    }
}
