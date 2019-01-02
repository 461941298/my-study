package com.yjh.study.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RpcProxy {

    public static <T> T getProxy(final Class<T> interfaceClass) {
        //第一参数是类加载哭器
        //第二参数是对象要实现的接口类
        //第三参数是实现了InvocationHandler接口的对象
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new DynProxy(interfaceClass));
    }

    private static class DynProxy implements InvocationHandler {
        private final String ip;
        private final Integer port;
        private final Class interfaceClass;

        public DynProxy(Class interfaceClass) {
            this.ip = "127.0.0.1";
            this.port = 9090;
            this.interfaceClass = interfaceClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Socket socket = null;
            ObjectOutputStream objectOutputStream = null;
            ObjectInputStream objectInputStream = null;

            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(this.ip, this.port));
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());

                //接口名
                objectOutputStream.writeUTF(this.interfaceClass.getSimpleName());
                //方法名
                objectOutputStream.writeUTF(method.getName());
                //参数类型
                objectOutputStream.writeObject(method.getParameterTypes());
                //参数值
                objectOutputStream.writeObject(args);
                //立即发送
                objectOutputStream.flush();

                //接收服务端的应答
                Object result = objectInputStream.readObject();
                System.out.println("服务端的应答是 " + result);

                return result;
            } finally {
                if (objectInputStream != null) objectInputStream.close();
                if (objectOutputStream != null) objectOutputStream.close();
                if (socket != null) socket.close();
            }
        }

    }


}
