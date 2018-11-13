package com.yjh.study.dao;

import com.yjh.study.entity.Order;

/**
 * @author yjh
 * @discrption
 */
public interface OrderDao {

    Order getById(Integer id);

    void updateMoney(double money);
}
