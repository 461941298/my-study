package com.yjh.study.cap5;

import com.yjh.study.cap1.Person;
import com.yjh.study.cap4.config.Cap4MainConfig;
import com.yjh.study.cap5.config.Cap5MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Cap5Test {

    @org.junit.Test
    public void test01(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap5MainConfig.class);
        System.out.println("容器创建完成----");
        String[] beanNames = app.getBeanDefinitionNames();
        Arrays.asList(beanNames).forEach(
                System.out::println
        );


    }
}
