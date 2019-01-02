package com.yjh.study;

import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Controller
public class OrderInfo {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
//        同步调用
//        String userView = userService.getDetail(id);
//        String orderView = orderService.getDetail(id);

        userService.getDetail(id);
        Future<Object> future1 = RpcContext.getContext().getFuture();

        orderService.getDetail(id);
        Future<Object> future2 = RpcContext.getContext().getFuture();

        Object userView = null;
        Object orderView = null;
        try {
            orderView = future2.get();
            userView = future1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        request.setAttribute("userView", userView);
        request.setAttribute("orderView", orderView);

        ModelAndView index = new ModelAndView("index");
        index.addObject("userView", userView);
        index.addObject("orderView", orderView);
        return index;
    }
}
