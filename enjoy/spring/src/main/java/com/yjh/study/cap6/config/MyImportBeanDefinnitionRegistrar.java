package com.yjh.study.cap6.config;

import com.yjh.study.cap6.bean.Pig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinnitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 把所有需要添加到容器的bean加入
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry               BeanDefinition注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        //检索容器中是否存在dog实例
        final boolean b1 = !registry.containsBeanDefinition("com.yjh.study.cap6.bean.Dog");

        if(b1){
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Pig.class);
            registry.registerBeanDefinition("pig",  beanDefinition);
        }

    }
}
