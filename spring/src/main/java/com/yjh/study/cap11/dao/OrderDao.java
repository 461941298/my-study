package com.yjh.study.cap11.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.beans.Transient;
import java.util.Date;

@Repository
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void insert(){
        String sql = "INSERT INTO `order`(`time`, `money`, `status`) VALUES (?,?,?)";
        jdbcTemplate.update(sql, new Date(), 1, 0);
    }
}
