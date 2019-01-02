package com.yjh.study.nio.cap3.nio;


/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 类说明：nio通信服务端
 */
public class NioServer {

    private static NioServerHandle nioServerHandle;

    public static void start(){
        if(nioServerHandle !=null)
            nioServerHandle.stop();
        nioServerHandle = new NioServerHandle(9698);
        new Thread(nioServerHandle,"Server").start();
    }
    public static void main(String[] args){
        start();
    }

}
