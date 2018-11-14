package com.yjh.study.cap13.service;

import com.yjh.study.cap13.entity.Order;
import com.yjh.study.cap13.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
