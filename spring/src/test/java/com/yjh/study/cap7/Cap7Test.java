package com.yjh.study.cap7;

import com.yjh.study.cap7.config.Cap7MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Cap7Test {

    @org.junit.Test
    public void test01() throws Exception {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap7MainConfig.class);
        System.out.println("容器创建完成----");
        app.close();

    }
}
