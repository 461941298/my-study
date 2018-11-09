package com.yjh.study.cap7.config;

import com.yjh.study.cap1.Person;
import com.yjh.study.cap7.bean.Bike;
import com.yjh.study.cap7.bean.Jeep;
import com.yjh.study.cap7.bean.Train;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.yjh.study.cap7.bean")
public class Cap7MainConfig {

    /*
    bean创建过程
    1. 创建容器
    2. 创建并初始化容器相关的所有BeanPostProcessor（前、后置处理器）
    3. 刷新并用前后处理器增强bean
     */
    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加Person");
        return new Person("person", 18);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Bike bike() {
        return new Bike();
    }

    @Bean
    public Train train() {
        return new Train();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Jeep jeep() {
        return new Jeep();
    }
}
