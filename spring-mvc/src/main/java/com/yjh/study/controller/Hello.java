package com.yjh.study.controller;

import com.yjh.study.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yjh
 * @discrption
 */
@Controller
public class Hello {

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
}
