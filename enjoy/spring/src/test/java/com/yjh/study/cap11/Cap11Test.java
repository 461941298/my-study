package com.yjh.study.cap11;

import com.yjh.study.cap11.config.Cap11MainConfig;
import com.yjh.study.cap11.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Cap11Test {

    @org.junit.Test
    public void test1(){
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Cap11MainConfig.class);
        final OrderService orderService = context.getBean(OrderService.class);
        orderService.insert();

    }
}
