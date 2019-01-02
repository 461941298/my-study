package cn.enjoy.order.listener;

import cn.enjoy.order.utils.LoadBalance;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VULCAN on 2018/7/28.
 */


public class InitListener implements ServletContextListener {

    private static final String BASE_SERVICES = "/services";
    private static final String SERVICE_NAME = "/products";

    private ZkClient zkClient;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            zkClient = new ZkClient("192.168.1.190:2181", 5000);
            //添加监听器,简单粗暴，如果节点内容发生改变则重新加载所有节点信息
            zkClient.subscribeChildChanges(BASE_SERVICES + SERVICE_NAME, new IZkChildListener() {
                @Override
                public void handleChildChange(String s, List<String> list) throws Exception {
                     updateServiceList();
                }
            });

            updateServiceList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateServiceList() {
        try {
            List<String> children = zkClient.getChildren(BASE_SERVICES + SERVICE_NAME);
            List<String> newServerList = new ArrayList<>();
            for (String subNode : children) {
                String host = zkClient.readData(BASE_SERVICES + SERVICE_NAME + "/" + subNode);
                System.out.println("host:" + host);
                newServerList.add(host);
            }
            LoadBalance.SERVICE_LIST = newServerList;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
