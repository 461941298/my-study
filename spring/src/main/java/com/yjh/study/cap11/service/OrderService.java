package com.yjh.study.cap11.service;

import com.yjh.study.cap11.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Transactional
    public void insert(){
        orderDao.insert();

//        int a = 1/0;
    }
}
