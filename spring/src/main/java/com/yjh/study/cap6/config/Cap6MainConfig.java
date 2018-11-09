package com.yjh.study.cap6.config;

import com.yjh.study.cap1.Person;
import com.yjh.study.cap5.config.LinuxCondition;
import com.yjh.study.cap5.config.WinCondition;
import com.yjh.study.cap6.bean.Cat;
import com.yjh.study.cap6.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {MyImportBeanDefinnitionRegistrar.class, MyImportSelector.class})
public class Cap6MainConfig {

    /**
     * 给容器中注册组件的方式：
     * 1. @Bean ： 导入第三方的类或包的组件
     * 2. 包扫描+组件的标注注解(@ComponentScan @Controller ...) : 一般针对自己写的类
     * 3. @Import ： 快速给容器导入一个组件
     *      a. @Import : 容器自动注册这个组件， Bean 的 id 为全类名
     *      b. ImportSelector : 是一个接口，返回需要导入到容器的组件的全类名数组
     *      c. ImportBeanDefinitionRegistrar : 可以手动添加组件到IOC容器中。所有Bean的注册都可以使用这个接口
     * 4. 使用spring提供的FactoryBean(工厂bean)进行注册
     * <p>
     * 注意：@Bean 有点简单
     *
     * @return
     */
    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加Person");
        return new Person("person", 18);
    }

    @Bean
    public MyFactoryBean myFactoryBean(){
        return new MyFactoryBean();
    }
}
