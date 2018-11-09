package com.yjh.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yjh
 * @discrption
 */
@Controller
public class Hello {

    @GetMapping("/world")
    @ResponseBody
    public String hello() {
        return "hello world";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
