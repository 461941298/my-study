package com.yjh.study.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Test {

    public static void main(String[] args) {

        ServiceLoader<Hello> hellos = ServiceLoader.load(Hello.class);

        Iterator<Hello> iterator = hellos.iterator();

        while (iterator.hasNext()){
            iterator.next().hello();
        }
    }
}
