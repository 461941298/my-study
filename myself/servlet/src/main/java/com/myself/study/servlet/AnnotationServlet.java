package com.myself.study.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/annotation", initParams = {@WebInitParam(name = "name", value = "yjh"),
        @WebInitParam(name = "age", value = "27")})
public class AnnotationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = this.getInitParameter("name");
        String age = this.getInitParameter("age");

        PrintWriter writer = resp.getWriter();
        writer.write("name = " + name + " age = " + age);

        writer.flush();
        writer.close();
    }
}
