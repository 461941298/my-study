package com.yjh.study.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yjh
 * @discrption
 */
public class DispatcherServlet extends HttpServlet {

    private Map<String, Class<?>> beanClass = new HashMap<>();

    @Override
    public void init() throws ServletException {
        //1. 扫瞄指定的包，加载beanClass
        scanPackage("com.yjh.study");
        //2. 实现IOC容器
        //3. 实现依赖注入
        //4. 实现url与方法的映射匹配
    }

    /**
     * 扫瞄指定的包，加载beanClass
     *
     * @param basePackage
     */
    private void scanPackage(String basePackage) {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 拿到url
        //2. 找到处理url的方法
        //3. 解析请求参数
        //4. 使用方法的invoke
    }
}
