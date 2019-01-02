package com.yjh.study.server;

import com.yjh.study.server.api.Say;
import com.yjh.study.server.impl.SayImpl;
import com.yjh.study.server.register.RegisterCenter;

public class Server {

    public static void main(String[] args) {
        new Thread(
                () -> {
                    RegisterCenter registerCenter = new RegisterCenter(9090);
                    registerCenter.regist(Say.class.getSimpleName(), SayImpl.class);

                    registerCenter.start();
                }
        ).start();
    }
}
