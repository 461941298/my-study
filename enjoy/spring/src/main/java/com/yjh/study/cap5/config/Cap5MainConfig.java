package com.yjh.study.cap5.config;

import com.yjh.study.cap1.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Cap5MainConfig {

    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加Person");
        return new Person("person", 18);
    }

    // 如果操作系统为windows把Bean注入到容器中
    @Conditional(WinCondition.class)
    @Bean("yjh")
    public Person yjh(){
        System.out.println("给容器中添加Person");
        return new Person("yjh", 27);
    }

    // 如果操作系统为linux把Bean注入到容器中
    @Conditional(LinuxCondition.class)
    @Bean("mm")
    public Person mm(){
        System.out.println("给容器中添加Person");
        return new Person("mm", 22);
    }
}
