package com.yjh.study.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IOrderImpl extends UnicastRemoteObject implements IOrder {

    public IOrderImpl() throws RemoteException {
        super();
    }

    @Override
    public String pay(String id) throws RemoteException {
        return "订单号是" + id;
    }
}
