package com.yjh.study.service;

import com.yjh.study.annotation.Component;

/**
 * @author yjh
 * @discrption
 */
@Component("UserService")
public class UserService {

    public String getInfo(String name, String age) {
        return "user.name = " + name + " user.age = " + age;
    }
}
