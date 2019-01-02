package com.yjh.study.cap9.controller;

import com.yjh.study.cap9.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

    @Autowired
    @Qualifier("orderService2")
    public OrderService orderService2;
}
