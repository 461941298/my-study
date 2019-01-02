package com.myself.study.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

public class FormHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //===============参数获取================

        //得到表单参数名迭代器
        Enumeration<String> parameterNames = req.getParameterNames();
        System.out.println("迭代器中的参数名：");
        while (parameterNames.hasMoreElements()) {
            System.out.println(parameterNames.nextElement());
        }

        //表单参数都放到一个map中
        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println("map中的参数：");
        for (String key : parameterMap.keySet()) {
            System.out.println(parameterMap.get(key).toString());
        }

        //得到指定key值的单个参数value
        String name = req.getParameter("name");
        System.out.println("单个参数：name = " + name);
        //得到指定key值的多个参数values
        String[] likes = req.getParameterValues("like");
        System.out.println("多个参数: like = " + likes.toString());


        //================session跟踪==============
        HttpSession session = req.getSession(false);
        Object userName = session.getAttribute("name");
        if (!userName.equals(name)) {
            //将session失效
            session.invalidate();
            throw new RuntimeException("用户信息不匹配---");
        } else {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                String comment = cookie.getComment();
                String domain = cookie.getDomain();
                String path = cookie.getPath();
                String value = cookie.getValue();
                System.out.println(
                        "cookies 的名字 ：" + cookieName
                                + " 值：" + value
                                + " 备注 ：" + comment
                                + " domain : " + domain
                                + " path :" + path
                );
            }

            //转发
            req.getRequestDispatcher("/servlet/user/info").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
