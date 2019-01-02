package com.yjh.study.net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public static void main(String[] args) throws Exception {

        //创建一个ServerSocket，端口是8888
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务启动，端口号为8888");
        while (true){
            //等待客户端的请求，如果有分配一个socket
            Socket socket = serverSocket.accept();

            //根据标准输入构造一个BufferedReader对象
            final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String buffer = null;
            while ((buffer = reader.readLine())!=null &&!buffer.equals("")){
                System.out.println(buffer);
            }

            //通过Socket对象等到输出流，构造BufferedWrite
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //模拟http请求头信息
            writer.write("HTTP/1.1 200 OK \r\n Content-Type:text/html \r\ncharset=UTF-8 \r\n\r\n");
            writer.write("<html><head><title>http请求</title></head>" +
                    "<body><h1>这是一个http请求</h1></body></html>");
            //刷新输出流，使数据立马发送
            writer.flush();
            //关闭
            reader.close();
            writer.close();
        }

    }
}
