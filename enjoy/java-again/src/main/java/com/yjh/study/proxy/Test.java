package com.yjh.study.proxy;

public class Test {

    public static void main(String[] args) {
        Fun fun = new Proxy(new Target()).getProxy();
        fun.run();
    }
}
