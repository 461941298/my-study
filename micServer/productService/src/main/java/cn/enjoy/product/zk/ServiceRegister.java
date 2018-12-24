package cn.enjoy.product.zk;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by VULCAN on 2018/7/28.
 */
public class ServiceRegister {

    private static final String BASE_SERVICES = "/services";
    private static final String SERVICE_NAME = "/products";

    public static void register(String address, int port) {

        ZkClient zkClient = new ZkClient("192.168.1.190:2181", 5000);

        if (!zkClient.exists(BASE_SERVICES)) {
            zkClient.createPersistent(BASE_SERVICES);
        }

        if (!zkClient.exists(BASE_SERVICES + SERVICE_NAME)) {
            zkClient.createPersistent(BASE_SERVICES + SERVICE_NAME);
        }

        zkClient.createEphemeralSequential(BASE_SERVICES + SERVICE_NAME + "/child",
                address + ":" + port);
    }

}
