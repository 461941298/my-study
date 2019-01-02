package com.yjh.study.cap12;

import com.sun.org.apache.xpath.internal.operations.String;
import com.yjh.study.cap12.bean.Moon;
import com.yjh.study.cap12.bean.Sun;
import com.yjh.study.cap12.config.Cap12MainConfig;
import com.yjh.study.cap6.bean.Pig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Cap12Test {

    @Test
    public void test1(){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Cap12MainConfig.class);
        context.getBean(Moon.class);
        context.getBean(Sun.class);
        context.close();

    }


    public static void main(String[] args) {
    }
}
