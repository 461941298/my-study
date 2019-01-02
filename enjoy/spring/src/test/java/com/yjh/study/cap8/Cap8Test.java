package com.yjh.study.cap8;

import com.yjh.study.cap8.config.Cap8MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class Cap8Test {

    @org.junit.Test
    public void test01() throws Exception {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap8MainConfig.class);
        System.out.println("容器创建完成----");

        final String[] beanDefinitionNames = app.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

        final ConfigurableEnvironment environment = app.getEnvironment();
        System.out.println("environment " + environment.getProperty("user.sex.man"));
        final Object user = app.getBean("user");
        System.out.println(user);
        app.close();

    }
}
