package com.yjh.study.proxy;

public class Target implements Fun {
    @Override
    public void run() {
        System.out.println("正在跑步");
    }
}
