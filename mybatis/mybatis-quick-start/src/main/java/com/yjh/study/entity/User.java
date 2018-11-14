package com.yjh.study.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yjh
 * @discrption
 */
public class User implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private Role role;
    private List<Phone> phones = new ArrayList<>();
    private List<Home> homes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Home> getHomes() {
        return homes;
    }

    public void setHomes(List<Home> homes) {
        this.homes = homes;
    }
}
