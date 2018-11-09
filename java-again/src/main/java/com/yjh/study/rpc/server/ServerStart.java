package com.yjh.study.rpc.server;

import com.yjh.study.rpc.regist.Regist;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ServerSocket serverSocket = new ServerSocket(8090);
        Regist.registMap.put(Server.class.getName(), Server.class);
        InputStream inputStream;
        OutputStream outputStream;
        while (true){
            final Socket client = serverSocket.accept();
            inputStream = client.getInputStream();
            outputStream = client.getOutputStream();

            final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            //类名
            String className = objectInputStream.readUTF();
            Class cls = Class.forName(className);
            //方法名
            String methodName = objectInputStream.readUTF();
            //参数类型
            Class[] paramTypes = (Class[]) objectInputStream.readObject();
            Method method = cls.getMethod(methodName, paramTypes);
            //参数值
            Object[] paramValues = (Object[]) objectInputStream.readObject();

            final Object instance = Regist.registMap.get(className).newInstance();
            final Object result = method.invoke(instance, paramValues);

            objectOutputStream.writeObject(result);

            objectOutputStream.flush();
            objectInputStream.close();
            objectOutputStream.close();
            client.close();

        }
    }
}
