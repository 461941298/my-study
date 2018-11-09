package com.yjh.study.cap4;

import com.yjh.study.cap1.Person;
import com.yjh.study.cap4.config.Cap4MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Cap4Test {

    @org.junit.Test
    public void test01(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap4MainConfig.class);
        System.out.println("IOC容器创建完成");
        final Person bean = app.getBean(Person.class);


    }
}
