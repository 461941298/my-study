package com.yjh.study.controller;

import com.yjh.study.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author yjh
 * @discrption
 */
@Controller
public class Hello {

    private static final Queue<DeferredResult<String>> QUEUE = new ConcurrentLinkedDeque<>();

    @Autowired
    private HelloService helloService;

    @GetMapping("/world")
    @ResponseBody
    public String hello() {
        System.out.println(Thread.currentThread());
        return helloService.hello();
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/asyn-req1")
    @ResponseBody
    public Callable<String> asyn1() {
        System.out.println("主线程" + Thread.currentThread() + "开始" + System.currentTimeMillis());
        final Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("副线程" + Thread.currentThread() + "开始" + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("副线程" + Thread.currentThread() + "线束" + System.currentTimeMillis());
                return "yjh111";
            }
        };
        System.out.println("主线程" + Thread.currentThread() + "结束" + System.currentTimeMillis());
        return callable;
    }

    @GetMapping("/asyn-req2")
    @ResponseBody
    public DeferredResult<String> asyn2() {
        final DeferredResult<String> stringDeferredResult = new DeferredResult<>((long) 5000, "fail");
        QUEUE.offer(stringDeferredResult);
        return stringDeferredResult;
    }

    @GetMapping("/get")
    @ResponseBody
    public Object get() {
        final DeferredResult<String> stringDeferredResult = QUEUE.poll();
        stringDeferredResult.setResult("yjh1233");
        return stringDeferredResult.getResult();
    }


}
