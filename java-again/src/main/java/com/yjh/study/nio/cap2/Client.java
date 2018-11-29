package com.yjh.study.nio.cap2;

import java.util.Scanner;

/**
 * 客户端启用两个线程，主线程负责向服务端写数据，副线程负责读取服务端数据
 *
 * @author yjh
 * @discrption
 */
public class Client {

    private static ClientHandler clientHandler;

    private static void start() {
        if (clientHandler == null) {
            clientHandler = new ClientHandler("127.0.0.1", 9090);
        }

        new Thread(clientHandler).start();
    }

    private static boolean sendMsg(String str) {
        clientHandler.sendMsg(str);
        return true;
    }

    public static void main(String[] args) {
        start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            sendMsg(scanner.next());
        }
    }
}
