package com.yjh.study.zkClient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class SessionDemo {

    private final static String CONNECTION_STR = "192.168.1.190:2181";

    public static void main(String[] args) throws Exception {
        ZkClient zkClient = new ZkClient(CONNECTION_STR, 5000);

        // true 递归创建父节点
//        zkClient.createPersistent("/zkclient/zkclient1/zkclient1-1/zkclient1-1-1", true);

        //删除节点
//        zkClient.deleteRecursive("/zkclient");

        //获取子节点
//        List<String> list = zkClient.getChildren("/zkclient");
//        System.out.println(list);


        //watcher
        zkClient.subscribeDataChanges("/zkclient", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点 " + s + " 值发生变化" + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("节点值被删除");
            }
        });

        zkClient.writeData("/zkclient", "hh");
        zkClient.deleteRecursive( "/zkclient");
        Thread.sleep(3000);
    }
}
