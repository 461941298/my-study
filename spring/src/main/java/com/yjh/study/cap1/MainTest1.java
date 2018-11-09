package com.yjh.study.cap1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest1 {

    public static void main(String[] args) {
        // 把beans.xml读到容器中
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 从容器中取出bean
        Person person = (Person) context.getBean("person");
        System.out.println(person);

    }
}
