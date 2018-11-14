package com.yjh.study.cap13.service;

import com.yjh.study.cap13.config.Cap13MainConfig;
import com.yjh.study.cap13.entity.Order;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yjh
 * @discrption
 */
public class OrderServiceTest {

    @Test
    public void getById() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Cap13MainConfig.class);
        OrderService orderService = context.getBean(OrderService.class);
        Order order = orderService.getById(7);
        System.out.println(order);
    }
}