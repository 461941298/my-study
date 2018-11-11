package com.yjh.study.controller;

import com.yjh.study.annotation.Component;
import com.yjh.study.annotation.Qualifer;
import com.yjh.study.annotation.RequestMapping;
import com.yjh.study.annotation.RequestParam;
import com.yjh.study.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yjh
 * @discrption
 */
@Component("UserController")
@RequestMapping("/user")
public class UserController {

    @Qualifer("UserService")
    private UserService userService;

    @RequestMapping("/info")
    public void getInfo(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("name") String name, @RequestParam("age") String age) throws IOException {
        String result = userService.getInfo(name, age);
        response.getWriter().write(result);
    }


}
