package com.yjh.study.cap9.service;

import com.yjh.study.cap9.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String name;

    public OrderService() {
    }

    public OrderService(String name) {
        this.name = name;
    }

    @Autowired
    private OrderDao orderDao;

    @Override
    public String toString() {
        return "OrderService{" +
                "name='" + name + '\'' +
                ", orderDao=" + orderDao +
                '}';
    }
}
