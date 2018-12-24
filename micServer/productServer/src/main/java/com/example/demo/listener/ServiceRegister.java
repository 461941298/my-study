package com.example.demo.listener;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class ServiceRegister {
    private static final String BASE_SERVICES = "/services";
    private static final String SERVICE_NAME = "/products";

    private static ZkClient zkClient;

    public static void register(String hostAddress, int port) {
        zkClient = new ZkClient("192.168.1.190:2181");
        if (!zkClient.exists(BASE_SERVICES + SERVICE_NAME)) {
            zkClient.create(BASE_SERVICES + SERVICE_NAME, "", CreateMode.PERSISTENT);
        }

        zkClient.create(BASE_SERVICES + SERVICE_NAME + "/child",
                hostAddress + ":" + port,
                CreateMode.EPHEMERAL_SEQUENTIAL);

    }

    public static void main(String[] args) {
        zkClient = new ZkClient("192.168.1.190:2181");
    }
}
