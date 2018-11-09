package com.yjh.study.servlet;

import com.yjh.study.servlet.init.MyService;
import com.yjh.study.servlet.init.OtherService;
import com.yjh.study.servlet.service.MyFilter;
import com.yjh.study.servlet.service.MyListener;
import com.yjh.study.servlet.service.MyServlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

//容器启动时将@HandlesTypes指定的这些类型的子类，接口等传递过来
@HandlesTypes(value = {MyService.class, OtherService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {

    /**
     * tomcat启动时加载应用时会先运行 onStartup 方法
     *
     * @param set            感兴趣的类型的所有子类
     * @param servletContext 代表当前web应用的ServletContext，一个web应用一个ServletContext
     *                       1. 使用servletContext注册web组件（servlet, listener, filter）
     *                       2. 使用编码的方式在项目启动的时候给ServletContext添加组件
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("感兴趣的类型：");
        for (Class<?> aClass : set) {
            //当传进来后，可以根据自己需要利用反射创建对象
            System.out.println(aClass);
        }

        //注册servlet组件
        final ServletRegistration.Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
        //设置映射路径
        myServlet.addMapping("/my-servlet");

        //注册监听器
        servletContext.addListener(MyListener.class);

        //注册过滤器
        final FilterRegistration.Dynamic myFilter = servletContext.addFilter("myFilter", MyFilter.class);
        //添加filter的映射信息，可以专门指定拦截哪个servlet
        myFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }
}
