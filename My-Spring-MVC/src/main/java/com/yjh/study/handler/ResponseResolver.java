package com.yjh.study.handler;

import com.yjh.study.annotation.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yjh
 * @discrption
 */
@Component("ResponseResolver")
public class ResponseResolver implements ArgumentResolver {
    @Override
    public boolean support(Method method, int index) {
        return method.getParameters()[index].getType().isAssignableFrom(HttpServletResponse.class);
    }

    @Override
    public Object resolve(HttpServletRequest req, HttpServletResponse resp, Method method, int index) {
        return resp;
    }
}
