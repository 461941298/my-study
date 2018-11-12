package com.yjh.study.handler;

import com.yjh.study.annotation.Component;
import com.yjh.study.annotation.RequestMapping;
import com.yjh.study.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author yjh
 * @discrption
 */
@Component("RequestParamResolver")
public class RequestParamResolver implements ArgumentResolver {
    @Override
    public boolean support(Method method, int index) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation annotation : parameterAnnotations[index]) {
            if (annotation instanceof RequestParam) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object resolve(HttpServletRequest req, HttpServletResponse resp, Method method, int index) {
        String paramName = null;
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation annotation : parameterAnnotations[index]) {
            if (annotation instanceof RequestParam) {
                paramName = ((RequestParam) annotation).value();
                break;
            }
        }
        return req.getParameter(paramName);
    }
}
