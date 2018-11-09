package com.yjh.study.cap10;

import com.yjh.study.cap10.aop.Calculator;
import com.yjh.study.cap10.config.Cap10MainConfig;
import com.yjh.study.cap6.bean.Dog;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Cap10Test {

    @org.junit.Test
    public void test01() throws Exception {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap10MainConfig.class);
        Calculator calculator = app.getBean(Calculator.class);
        calculator.div(3, 1);
    }

    @org.junit.Test
    public void test02() throws Exception {
        final Run proxyInstance = (Run) Proxy.newProxyInstance(Dog.class.getClassLoader(),
                new Class[]{Run.class}, new RunInvocationHandler(new Dog()));

        proxyInstance.run();
    }

    static class RunInvocationHandler implements InvocationHandler {

        private Run run;

        public RunInvocationHandler(Run run) {
            this.run = run;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("前、、、");
            Object result = method.invoke(run, args);
            System.out.println("后、、、");
            return result;
        }
    }


    interface Run {
        void run();

    }

    static class Dog implements Run {

        @Override
        public void run() {
            System.out.println("dog run");
        }
    }
}
