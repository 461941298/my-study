package com.yjh.study.cap7.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Train implements InitializingBean, DisposableBean {

    public Train() {
        System.out.println(" 火车构造。。。。");
    }

    // bean 销毁时调用此方法
    @Override
    public void destroy() throws Exception {
        System.out.println(" 火车销毁。。。");

    }

    // bean 属性和初始化完成时调用
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" afterPropertiesSet");

    }
}
