package com.yjh.study.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxy<T> implements InvocationHandler {

    private Class<T> t;

    public ClientProxy(Class<T> t) {
        this.t = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        return null;
    }

    public T getProxy() {
        return (T) Proxy.newProxyInstance(t.getClassLoader(), new Class[]{t}, this);
    }
}
