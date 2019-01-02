package com.yjh.study.rmi;

import java.rmi.Naming;

public class Client {

    public static void main(String[] args) throws Exception {
        //通过rmi发现服务并转成一个对象
        IOrder order = (IOrder) Naming.lookup("rmi://localhost:8888/order");
        System.out.println(order.pay("1234"));
    }
}
