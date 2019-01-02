package com.yjh.study.dao;

import com.yjh.study.entity.Phone;

import java.util.List;

/**
 * @author yjh
 * @discrption
 */
public interface PhoneDao {

    List<Phone> findByUserId(int userId);
}
