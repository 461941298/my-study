package com.yjh.study.cap13.mapper;

import com.yjh.study.cap13.entity.Order;
import com.yjh.study.cap13.view.OrderView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yjh
 * @discrption
 */
public interface OrderMapper {

    Order getById(Integer id);

    void updateMoney(@Param("money") double money, @Param("id") int id);

    void insert(Order order);

    List<OrderView> getAll();

    List<OrderView> selectIf(@Param("money") Double money, @Param("status") Integer status);

    List<OrderView> selectIfAndWhere(@Param("money") Double money, @Param("status") Integer status);

    void updateIfAndSet(Order order);

    void insertAndTrim(Order order);

    List<OrderView> getByMoneyForeach(double[] money);

    void insertByBatch(List<Order> order);

    List<OrderView> getChooseWhen(@Param("money") Double money, @Param("status")Integer status);
}
