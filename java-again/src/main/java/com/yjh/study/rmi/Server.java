package com.yjh.study.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) throws Exception{
        //接口实例化
        final IOrder order = new IOrderImpl();
        //本地的服务注册到端口中
        LocateRegistry.createRegistry(8888);
        //把实例绑定到本地端口的一个路径上
        Naming.bind("rmi://localhost:8888/order", order);
        System.out.println("服务器启动了、、、");
    }
}
