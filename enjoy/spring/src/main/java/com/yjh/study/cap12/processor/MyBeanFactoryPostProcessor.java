package com.yjh.study.cap12.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//BeanFactoryPostProcessor 用来处理 BeanPostProcessor 实例
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor。。。。。");
        // 拿到所有bean的定义（已经加载到beanFactory，但是bean实例还没有创建）
        int count = beanFactory.getBeanDefinitionCount();
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        System.out.println("当前beanFactory中有"+count+"个Bean");
        System.out.println(Arrays.toString(beanDefinitionNames));

    }
}
