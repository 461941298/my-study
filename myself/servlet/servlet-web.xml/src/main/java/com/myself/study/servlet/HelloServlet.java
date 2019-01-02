package com.myself.study.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

    private String msg;

    @Override
    public void init() throws ServletException {
        System.out.println("HelloServlet 开始初始化---");
        msg = "Servlet";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应内容类型
        resp.setContentType("text/html");
        //拿到输出流
        PrintWriter writer = resp.getWriter();
        //向客户端输出信息
        writer.println("<h1> Hello " + msg + "</h1>");
        //从上下文中获取操作系统信息
        writer.println("服务运行在 " + this.getServletContext().getAttribute("os.name") + " 系统上");
        //浏览器上换行
        writer.println("<br><br>");
        //获取<init-param>中的参数
        writer.println("服务的作者 " + this.getInitParameter("author"));

        //浏览器上换行
        writer.println("<br><br>");
        //上下文中添加信息
        this.getServletContext().setAttribute("love", "mm");
        //修改上下文中信息
        this.getServletContext().setAttribute("love", "苗苗");
        System.out.println(this.getServletContext().getAttribute("love"));

        writer.flush();
        writer.close();
    }


    @Override
    public void destroy() {
        System.out.println("HelloServlet 将要被销毁");
        System.out.println("msq=" + msg);
        msg = null;
    }
}
