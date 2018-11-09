package com.yjh.study.cap3.config;

import com.yjh.study.cap1.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class Cap3MainConfig {

    /*
        prototype: 多实例，IOC容器创建时并不创建多实例对象，而是在使用或获取时才创建
        singleton: 单实例，IOC容器创建时就创建单实例对象
        request: 主要针对web应用，递交一次请求创建一个实现
        session: 同一个session创建一个实例
     */
    @Scope("prototype")
    @Bean
    public Person person() {
        System.out.println("给容器中添加Person");
        return new Person("mm", 22);
    }
}
