package com.yjh.study.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveDemo {

    public static void main(String[] args) throws IOException {
        //创建一个DatagramSocket实例，把实例绑定到本机的10005端口
        final DatagramSocket datagramSocket = new DatagramSocket(10005);
        final byte[] bytes = new byte[1024];
        //以一个空数据创建DatagramPacket，这个对象用来接收DatagramSocket中的数据
        final DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        while (true){
            //接收到了数据包
            datagramSocket.receive(datagramPacket);
            //获取接收的数据
            final byte[] data = datagramPacket.getData();
            //把数组转成字符
            final String str = new String(data);
            //如果数据包中的信息为88，则跳出并关闭
            if("88".equals(str)){
                break;
            }
            System.out.println(str);
            //关闭
            datagramSocket.close();

        }
    }
}
