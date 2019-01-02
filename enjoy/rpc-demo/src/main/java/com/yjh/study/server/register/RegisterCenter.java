package com.yjh.study.server.register;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterCenter {

    //服务注册容器 key为接口全名， value为具体实现
    private static final Map<String, Class> serverMap = new HashMap();
    //服务端口号
    private Integer port;
    //使用线程池处理客户端连接过来的任务
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public RegisterCenter(Integer port) {
        this.port = port;
    }

    public void regist(String interfaceClass, Class implClass) {
        serverMap.put(interfaceClass, implClass);
    }

    /**
     * 开启服务
     */
    public void start() {
        Socket clientSocket;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务开启");
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("接收到一条新请求");
                executor.execute(new Task(clientSocket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Task implements Runnable {

        private Socket client;

        public Task(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {

                ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());

                //从socket中拿到接口名
                String interfaceName = objectInputStream.readUTF();
                //从socket中拿到方法名
                String methodName = objectInputStream.readUTF();
                //从socket中拿到参数类型
                Class<?>[] paramTypes = (Class<?>[]) objectInputStream.readObject();
                //从socket中拿到参数值
                Object[] paramValues = (Object[]) objectInputStream.readObject();

                //接口具体实现类
                Class serverClass = serverMap.get(interfaceName);
                //要调用的方法
                Method method = serverClass.getMethod(methodName, paramTypes);

                //拿到结果
                Object result = method.invoke(serverClass.newInstance(), paramValues);
                //将结果写给客户端
                objectOutputStream.writeObject(result);
                objectOutputStream.flush();

                //关闭流
                objectInputStream.close();
                objectOutputStream.close();
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
