package com.yjh.study.dao;

import com.yjh.study.entity.Home;

import java.util.List;

/**
 * @author yjh
 * @discrption
 */
public interface HomeDao {

    List<Home> findByUserId(Integer userId);
}
