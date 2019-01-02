package com.yjh.study.cap6;

import com.yjh.study.cap2.controller.OrderController;
import com.yjh.study.cap5.config.Cap5MainConfig;
import com.yjh.study.cap6.bean.Bird;
import com.yjh.study.cap6.bean.Dog;
import com.yjh.study.cap6.bean.Monkey;
import com.yjh.study.cap6.bean.Pig;
import com.yjh.study.cap6.config.Cap6MainConfig;
import com.yjh.study.cap6.config.MyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Cap6Test {

    @org.junit.Test
    public void test01() throws Exception {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap6MainConfig.class);
        System.out.println("容器创建完成----");
        String[] beanNames = app.getBeanDefinitionNames();
        Arrays.asList(beanNames).forEach(
                System.out::println
        );

        // 通过FactoryBean 注册进入容器中， 获取时也使用 FactoryBean 的id， 返回的是注入的 <T> 实例
        final Object bean1 = app.getBean("myFactoryBean");
        System.out.println(bean1.getClass());

        // 如果要返回 FactoryBean 本身的实例, 需要在 id 前加上 "&"
        final Object bean2 = app.getBean("&myFactoryBean");
        System.out.println(bean2.getClass());

        final Object bean3 = app.getBean(Bird.class);
        System.out.println(bean3.getClass());

        final Object bean4 = app.getBean("pig");
        System.out.println(bean4.getClass());

    }
}
