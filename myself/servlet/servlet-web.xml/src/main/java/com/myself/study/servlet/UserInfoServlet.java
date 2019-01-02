package com.myself.study.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应内容类型
        resp.setContentType("text/html");
        //拿到输出流
        PrintWriter writer = resp.getWriter();
        //拿到上下文
        ServletContext servletContext = this.getServletContext();
        writer.println("name " + servletContext.getAttribute("name"));
        //浏览器上换行
        writer.println("<br><br>");
        writer.println("age " + servletContext.getAttribute("age"));
        //浏览器上换行
        writer.println("<br><br>");
        writer.println("sex " + servletContext.getAttribute("sex"));

        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
