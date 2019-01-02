package com.yjh.study.service;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yjh
 * @discrption
 */
@WebServlet(value = "/asynorder", asyncSupported = true)
public class asynOrderService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("主线程开始..... "+Thread.currentThread()+" start "+ System.currentTimeMillis());

        //将请求放入异步环境，并对异步环境中的请求和响应初始化
        AsyncContext startAsync = req.startAsync();

        startAsync.start(new Runnable() {
            @Override
            public void run() {
                System.out.println("副线程开始..... "+Thread.currentThread()+" start "+ System.currentTimeMillis());
                try {
                    buyCards();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startAsync.complete();
                final ServletResponse response = startAsync.getResponse();
                try {
                    response.getWriter().write("order success...");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("副线程结束..... "+Thread.currentThread()+" end "+ System.currentTimeMillis());
            }
        });

        System.out.println("主线程结束..... "+Thread.currentThread()+" end "+ System.currentTimeMillis());
        //主线程的资源断开
    }

    public void buyCards() throws InterruptedException {
        System.out.println(Thread.currentThread()+"......JamesServlet.buyCards");
        //模拟业务操作
        Thread.sleep(5000);
    }
}
