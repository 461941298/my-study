package com.myself.study.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletInfo = this.getServletInfo();
        System.out.println(servletInfo);
        HttpSession session = req.getSession(true);
        session.setAttribute("name", "yjh");
        System.out.println("登录成功跳转到表单页面---");
        //重定向
        resp.sendRedirect("http://localhost:8080/form.html");
    }
}
