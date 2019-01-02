package com.yjh.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy implements InvocationHandler {

    private Fun fun;

    public Proxy(Fun fun) {
        this.fun = fun;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(this.fun, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("跑前热身");
    }

    private void before() {
        System.out.println("跑完洗个澡");
    }

    public Fun getProxy(){
        return (Fun) java.lang.reflect.Proxy.newProxyInstance(fun.getClass().getClassLoader(), new Class[]{Fun.class}, this);
    }

}
