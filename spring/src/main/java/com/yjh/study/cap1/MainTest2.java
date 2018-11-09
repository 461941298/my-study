package com.yjh.study.cap1;

import com.yjh.study.cap1.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest2 {

    public static void main(String[] arg){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        // 从容器中取出bean
        Person person = (Person) context.getBean("person");
        System.out.println(person);

        String[] namesForBean = context.getBeanNamesForType(Person.class);
        for (int i = 0; i < namesForBean.length; i++) {
            System.out.println(namesForBean[i]);
        }
    }
}
