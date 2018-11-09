package com.yjh.study.cap2.config;

import com.yjh.study.cap1.Person;
import com.yjh.study.cap2.controller.OrderController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
// 演示包含过滤器
//@ComponentScan(
//        value = "com.yjh.study.cap2",
//        useDefaultFilters = false,
//        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})}
//)

// 演示不包含过滤器
//@ComponentScan(
//        value = "com.yjh.study.cap2",
//        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})}
//)

// 演示指定类型
//@ComponentScan(
//        value = "com.yjh.study.cap2",
//        includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {OrderController.class})},
//        useDefaultFilters = false
//)

@ComponentScan(
        value = "com.yjh.study.cap2",
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})},
        useDefaultFilters = false
)
public class Cap2MainConfig {

    @Bean
    public Person person() {
        return new Person("mm", 22);
    }
}
