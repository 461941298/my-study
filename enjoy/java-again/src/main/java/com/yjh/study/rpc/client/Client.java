package com.yjh.study.rpc.client;


import com.yjh.study.rpc.server.Server;

public class Client {

    public static void main(String[] args) {
        Server server = (Server) new ClientProxy(Server.class).getProxy();
        server.run("yjh");
    }
}
