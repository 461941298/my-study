package com.yjh.study.cap5.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition{

    /**
     *
     * @param context  判断条件可以使用的上下文环境
     * @param metadata 注解的信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // 是否为win操作系统

        // 能获取到IOC容器正在使用的BeanFactory
        final ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 获取当前环境变量（包括操作系统的类型）
        final Environment environment = context.getEnvironment();

        // 获取操作系统名
        final String osName = environment.getProperty("os.name");

        // 判断是否为windows系统
        if(osName.contains("linux")){
            return true;
        }

        return false;
    }
}
