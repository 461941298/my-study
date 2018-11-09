package com.yjh.study.cap1;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Cap1Test {

    @org.junit.Test
    public void test01() {

        //使用spring的基本容器 DefaultListableBeanFactory。
        // 1. 创建IOC配置文件的抽象资源，bean.xml
        final ClassPathResource resource = new ClassPathResource("beans.xml");

        // 2. 创建一个BeanFactory
        final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 3. 创建一个BeanDefinition读取器
        final XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        // 4. 从定义好的资源位置读取配置信息
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);

        final Person person = beanFactory.getBean(Person.class);
        System.out.println(person);
    }

    @org.junit.Test
    public void test02() {

        final FileSystemXmlApplicationContext fileSystemXmlApplicationContext
                = new FileSystemXmlApplicationContext("classpath:/beans.xml");

        final Person person = fileSystemXmlApplicationContext.getBean(Person.class);
        System.out.println(person);
    }
}
