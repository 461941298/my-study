package com.yjh.study.handler;

import com.yjh.study.annotation.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @author yjh
 * @discrption
 */
@Component("ParamResolverAdapter")
public class ParamResolverAdapter {

    /**
     * 参数解析入口，具体的解析安排给具体的类
     *
     * @param req    请求
     * @param resp   响应
     * @param method 方法
     * @param beans  容器中的实例
     * @return
     */
    public Object[] resolver(HttpServletRequest req, HttpServletResponse resp,
                             Method method, Map<String, Object> beans) {

        Parameter[] parameters = method.getParameters();
        Object[] result = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            ArgumentResolver argumentResolver = getArgumentResolver(method, i, beans);
            if (argumentResolver == null) {
                throw new RuntimeException("不能解析" + method.getName() + "第" + i + "个元素");
            }
            result[i] = argumentResolver.resolve(req, resp, method, i);
        }
        return result;
    }

    /**
     * 通过策略模式找到可以正确解析参数的类
     *
     * @param method
     * @param index
     * @param beans
     * @return
     */
    private ArgumentResolver getArgumentResolver(Method method, int index, Map<String, Object> beans) {
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object bean = entry.getValue();
            if (bean instanceof ArgumentResolver) {
                if (((ArgumentResolver) bean).support(method, index)) {
                    return (ArgumentResolver) bean;
                }
            }
        }
        return null;
    }
}
