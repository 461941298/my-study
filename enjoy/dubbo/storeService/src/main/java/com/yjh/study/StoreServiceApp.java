package com.yjh.study;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class StoreServiceApp {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo.xml");
        context.start();
        System.out.println("服务开启");

        //保证服务一直开着
        System.in.read();
    }
}
