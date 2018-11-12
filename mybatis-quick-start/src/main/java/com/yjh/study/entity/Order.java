package com.yjh.study.entity;

import java.util.Date;

/**
 * @author yjh
 * @discrption
 */
public class Order {
    private Integer id;
    private Date time;
    private Double money;
    private Integer status;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", time=" + time +
                ", money=" + money +
                ", status=" + status +
                '}';
    }
}
