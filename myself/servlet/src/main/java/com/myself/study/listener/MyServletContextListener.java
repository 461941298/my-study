package com.myself.study.listener;

import jdk.internal.util.xml.impl.ReaderUTF8;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("容器初始化---");
        ServletContext servletContext = servletContextEvent.getServletContext();
        //获取操作系统名称，并将其添加到servlet上下文中
        servletContext.setAttribute("os.name", System.getProperty("os.name"));

        loaderData(servletContext);
    }

    private void loaderData(ServletContext servletContext) {
        InputStream inputStream = servletContext.getResourceAsStream("resources/data.properties");
        ReaderUTF8 readerUTF8 = new ReaderUTF8(inputStream);
        Properties properties = new Properties();
        try {
            properties.load(readerUTF8);
            String name = properties.getProperty("name");
            String age = properties.getProperty("age");
            String sex = properties.getProperty("sex");

            servletContext.setAttribute("name", name);
            servletContext.setAttribute("age", age);
            servletContext.setAttribute("sex", sex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("容器即将关闭---");
    }
}
