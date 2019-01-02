package com.yjh.study.rpc.server;

public class Server {

    public String run(String name) {
        System.out.println("Server : hello" + name);
        return "hello " + name;
    }

}
