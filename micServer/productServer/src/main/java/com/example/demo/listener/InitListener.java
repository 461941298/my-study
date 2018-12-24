package com.example.demo.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.InetAddress;

public class InitListener implements ServletContextListener {

    @Value("${server.port}")
    private int port;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            ServiceRegister.register(hostAddress,port);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
