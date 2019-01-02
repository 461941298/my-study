package com.yjh.study.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yjh
 * @discrption
 */
public interface ArgumentResolver {

    /**
     * 判断传入的方法的参数类型是不是能被当前实现类所解析
     *
     * @param method
     * @param index
     * @return
     */
    boolean support(Method method, int index);

    /**
     * 解析参数
     *
     * @param method 方法
     * @param index  参数下标
     * @return
     */
    Object resolve(HttpServletRequest req, HttpServletResponse resp, Method method, int index);
}
