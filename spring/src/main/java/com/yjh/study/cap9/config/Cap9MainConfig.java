package com.yjh.study.cap9.config;

import com.yjh.study.cap9.service.OrderService;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.yjh.study.cap9")
@PropertySource(value = "classpath:/my.properties")
public class Cap9MainConfig {

    @Primary
    @Bean
    public OrderService orderService1(){
        return new OrderService("order 1");
    }

    @Bean
    public OrderService orderService2(){
        return new OrderService("order 2");
    }
}
