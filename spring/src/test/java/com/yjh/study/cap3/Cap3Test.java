package com.yjh.study.cap3;

import com.yjh.study.cap1.Person;
import com.yjh.study.cap4.config.Cap4MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Cap3Test {

    @org.junit.Test
    public void test01(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap4MainConfig.class);
        System.out.println("容器创建完成----");
        String[] beanNames = app.getBeanDefinitionNames();
        Arrays.asList(beanNames).forEach(
                System.out::println
        );

        final Person bean1 = app.getBean(Person.class);
        final Person bean2 = app.getBean(Person.class);

        System.out.println(bean1 == bean2);

    }
}
