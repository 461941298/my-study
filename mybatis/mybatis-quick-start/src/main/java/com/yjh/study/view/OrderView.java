package com.yjh.study.view;

import com.yjh.study.entity.Order;

import java.util.Date;

/**
 * @author yjh
 * @discrption
 */
public class OrderView {

    private Integer orderId;
    private Double orderPrice;
    private Date createdTime;
    private Integer orderStatus;

    public Order convert2Order() {
        Order order = new Order();
        order.setId(this.orderId);
        order.setTime(this.createdTime);
        order.setMoney(this.orderPrice);
        order.setStatus(this.orderStatus);
        return order;
    }

    public OrderView() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderView{" +
                "orderId=" + orderId +
                ", orderPrice=" + orderPrice +
                ", createdTime=" + createdTime +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
