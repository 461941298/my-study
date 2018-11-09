package com.yjh.study.cap9;

import com.yjh.study.cap9.config.Cap9MainConfig;
import com.yjh.study.cap9.controller.OrderController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Cap9Test {

    @org.junit.Test
    public void test01() throws Exception {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap9MainConfig.class);
        System.out.println("容器创建完成----");

        final OrderController orderController = app.getBean(OrderController.class);
        System.out.println(orderController);


        System.out.println(orderController.orderService2);

    }

    @org.junit.Test
    public void test02() throws Exception {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap9MainConfig.class);

    }
}
