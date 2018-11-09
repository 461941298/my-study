package com.yjh.study.cap12.config;

import com.yjh.study.cap12.bean.Moon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.yjh.study.cap12")
public class Cap12MainConfig {

    @Bean
    public Moon getMoon(){
        return new Moon();
    }
}
