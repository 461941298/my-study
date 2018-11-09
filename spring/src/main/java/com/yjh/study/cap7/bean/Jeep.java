package com.yjh.study.cap7.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Jeep {

    public Jeep() {
        System.out.println(" jeep 构造。。。");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("jeep postConstruct ");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("jeep preDestroy ");

    }

    public void init(){
        System.out.println("jeep init ");

    }

    public void destroy(){
        System.out.println("jeep destroy ");

    }
}
