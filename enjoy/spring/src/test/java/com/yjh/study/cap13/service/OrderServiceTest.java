package com.yjh.study.cap13.service;

import com.yjh.study.cap13.config.Cap13MainConfig;
import com.yjh.study.cap13.entity.Order;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yjh
 * @discrption
 */
public class OrderServiceTest {

    @Test
    public void getById() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Cap13MainConfig.class);

        SqlSessionFactoryBean bean = context.getBean(SqlSessionFactoryBean.class);
        boolean aggressiveLazyLoading = bean.getObject().getConfiguration().isAggressiveLazyLoading();
        System.out.println(aggressiveLazyLoading);

        OrderService orderService = context.getBean(OrderService.class);
        Order order = orderService.getById(7);
        System.out.println(order);
    }

    @Test
    public void insert() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Cap13MainConfig.class);
        OrderService orderService = context.getBean(OrderService.class);

        orderService.insert();
    }
}