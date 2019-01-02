package com.yjh.study.cap1.config;

import com.yjh.study.cap1.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

    @Bean
    public Person person(){
        return new Person("mm", 22);
    }
}
