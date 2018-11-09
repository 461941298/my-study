package com.yjh.study.net.udp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class SendDemo {

    public static void main(String[] args) throws Exception {
        //创建一个DatagramDocket实例
        final DatagramSocket datagramSocket = new DatagramSocket();
        //使用键盘输入构建一个
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = bufferedReader.readLine())!= null){
            //如果是88，则跳出
            if("88".equals(line)){
                break;
            }
            //转成byte数据
            final byte[] bytes = line.getBytes();
            //创建一个数据包对象
            final DatagramPacket datagramPacket =
                    new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 10005 );
            //发送数据包
            datagramSocket.send(datagramPacket);
            //关闭
            datagramSocket.close();
        }

    }
}
