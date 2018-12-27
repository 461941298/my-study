package com.yjh.study.client;

import com.yjh.study.server.api.Say;

public class Client {
    public static void main(String[] args) {
        Say say = RpcProxy.getProxy(Say.class);
        System.out.println(say.hello("yjh"));
    }
}
