package com.yjh.study.nio.cap2;

/**
 * @author yjh
 * @discrption
 */
public class Server {

    private static ServerHandler serverHandler;

    public static void main(String[] args) {
        serverHandler = new ServerHandler(9090);

        new Thread(serverHandler).start();
    }
}
