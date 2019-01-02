package com.yjh.study.spi;

public class HelloWorld implements Hello {

    @Override
    public void hello() {
        System.out.println("hello world");
    }
}
