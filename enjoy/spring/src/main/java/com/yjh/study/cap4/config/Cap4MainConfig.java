package com.yjh.study.cap4.config;

import com.yjh.study.cap1.Person;
import org.springframework.context.annotation.*;

@Configuration
public class Cap4MainConfig {

    /*
    懒加载： 主要针对单实例bean：默认在容器启动时创建
    懒加载： 容器启动时并不创建对象，仅当第一次使用（获取）bean时创建并初始化
     */
    @Lazy
    @Bean
    public Person person() {
        System.out.println("给容器中添加Person");
        return new Person("mm", 22);
    }
}
