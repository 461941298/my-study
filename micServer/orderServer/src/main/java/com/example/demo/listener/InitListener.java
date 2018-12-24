package com.example.demo.listener;

import com.example.demo.utils.LoadBalance;
import org.I0Itec.zkclient.ZkClient;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

public class InitListener implements ServletContextListener {

    private static final String BASE_SERVICES = "/services";
    private static final String SERVICE_NAME = "/products";

    private ZkClient zkClient;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        zkClient = new ZkClient("192.168.1.190:2181");
        updateServiceList();
    }

    private void updateServiceList() {

        List<String> children = zkClient.getChildren(BASE_SERVICES + SERVICE_NAME);
        ArrayList<String> newServerList = new ArrayList<>();
        for (String subNode : children) {
            String data = zkClient.readData(BASE_SERVICES + SERVICE_NAME + "/" + subNode);
            newServerList.add(data);
        }
        LoadBalance.SERVICE_LIST = newServerList;
    }
}
