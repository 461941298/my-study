package com.yjh.study.entity;

import java.io.Serializable;

/**
 * @author yjh
 * @discrption
 */
public class Phone implements Serializable {

    private String number;
    private User user;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
