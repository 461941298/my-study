package com.yjh.study.cap10.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

/*
日志切面类的方法需要动态感知到div（）方法运行，
通知方法：
    1. 前置通知：logStart（）；在div（）之前运行（@Before）
    2. 后置通知：logEnd（）；在目标方法结束之后运行(@After)
    3. 返回通知：logReturn（）；在目标方法正常返回后运行(@AfterReturning)
    4. 异常通知：logException(); 在目标方法出现异常后运行(@AfterThrowing)
    5. 环绕通知：动态代理，需要手动执行joinPoint.proceed()(其实就是执行目标方法)(@Around)
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.yjh.study.cap10")
@PropertySource(value = "classpath:/my.properties")
public class Cap10MainConfig {


}
