package com.yjh.study.cap8.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.yjh.study.cap8.bean")
@PropertySource(value = "classpath:/my.properties")
public class Cap8MainConfig {


}
