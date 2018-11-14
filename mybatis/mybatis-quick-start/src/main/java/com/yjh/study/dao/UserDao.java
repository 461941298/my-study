package com.yjh.study.dao;

import com.yjh.study.entity.Role;
import com.yjh.study.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yjh
 * @discrption
 */
public interface UserDao {

    User findByName(@Param("name") String name);
    List<User> findByName2(@Param("name") String name);

    Role findById(int id);

    List<User> findWithPhonesByName(@Param("name") String name);

    List<User> findAllWithHome();
}
