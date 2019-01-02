package com.yjh.study.config;

import com.yjh.study.interceptor.WebInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author yjh
 * @discrption
 */
@EnableWebMvc
@EnableAsync
@ComponentScan(
        basePackages = "com.yjh.study",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
        }

)
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置ViewResolver
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //启用jsp，并设置前后缀
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器，并设置对所有请求进行拦截（默认也是这样）
        registry.addInterceptor(new WebInterceptor()).addPathPatterns("/**");

    }

    /**
     * 静态资源访问,静态资源交给tomcat来处理
     * 因为静态资源在DispatcherServlet中没有相应的映射路径，所以会出现静态资源不能访问的尴尬。
     * 解决方法有两种：
     * 1. 交由tomcat容器来处理，它会进行各种遍历直到找到资源（这样当然不行，当前就是这样处理的，后面再用第二种方法来改进）
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
    }

    /**
     * 静态资源访问正确处理
     * 2. 将静态资源统一放置，并设置指明对静态资源的正确访问路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static")
                .setCachePeriod(31556926);
    }
}
