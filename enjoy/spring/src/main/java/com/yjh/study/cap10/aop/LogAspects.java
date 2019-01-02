package com.yjh.study.cap10.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// 日志切面类
@Aspect
@Component
public class LogAspects {

    @Pointcut("execution(public int com.yjh.study.cap10.aop.Calculator.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + " 运行之前。。。参数列表是：{" + Arrays.asList(joinPoint.getArgs()) + "}");
    }

    @After("pointCut()")
    public void logEnd() {
        System.out.println("除法结束。。。");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(Object result) {
        System.out.println("除法正常返回。。。结果是：{" + result + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("除法异常。。。异常信息是：{" + exception + "}");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("@Around：执行目标方法之前。。。");
        final Object proceed = proceedingJoinPoint.proceed();
        System.out.println("@Around：执行目标方法之后。。。");
        return proceed;
    }


}
