package com.yjh.study.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yjh
 * @discrption
 */
public class MyMethod {

    private Object object;
    private Method method;

    public MyMethod(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public Object invoke(Object... args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(object, args);
    }


    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
