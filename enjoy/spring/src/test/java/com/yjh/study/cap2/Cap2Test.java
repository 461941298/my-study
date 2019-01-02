package com.yjh.study.cap2;

import com.yjh.study.cap2.config.Cap2MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Cap2Test {

    @org.junit.Test
    public void test01(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap2MainConfig.class);
        String[] beanNames = app.getBeanDefinitionNames();
        Arrays.asList(beanNames).forEach(
                System.out::println
        );
    }


}
