package com.yjh.study.ch3CAS;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class UseAtomicReference {



    static class User {
        String name;
        int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        User user = new User("yjh", 27);
        AtomicReference<User> userAtomicReference = new AtomicReference<>(user);

        System.out.println("没有赋值前的原子类 " + userAtomicReference.get());

        User updateUser = new User("mm", 22);
//        userAtomicReference.set(updateUser);
        userAtomicReference.compareAndSet(user, updateUser);
        System.out.println("赋值后的原子类 " + userAtomicReference.get());

        System.out.println("原user对象" + user);

//        证明：原子类中包装的引用对象与原引用对象并非同一个对象


    }
}
