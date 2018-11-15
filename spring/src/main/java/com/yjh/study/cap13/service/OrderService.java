package com.yjh.study.cap13.service;

import com.yjh.study.cap13.entity.Order;
import com.yjh.study.cap13.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yjh
 * @discrption
 */
@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    public Order getById(int orderId){
        return orderMapper.getById(orderId);
    }

    @Transactional
    public void insert(){

        Order order1 = new Order();
        order1.setMoney(88.0);
        order1.setTime(new Date());
        orderMapper.insert(order1);

        Order order2 = new Order();
        order2.setMoney(77.0);
        //数据库要求不能为null， 故意出错
//        order2.setTime(new Date());
        orderMapper.insert(order2);
    }
}
