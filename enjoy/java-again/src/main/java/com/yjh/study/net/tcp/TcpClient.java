package com.yjh.study.net.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient {

    public static void main(String[] args) throws IOException {
        String msg = "hello world";
        //创建一个Socket，
        final Socket socket = new Socket("127.0.0.1", 8888);
        //使用Socket创建一个PrintWriter进行写数据
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        //发送数据
        printWriter.println(msg);
        //刷新一下，使得服务器立马可以收到请求
        printWriter.flush();
        //关闭
        printWriter.close();
        socket.close();
    }
}
