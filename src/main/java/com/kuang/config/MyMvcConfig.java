package com.kuang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;
//拓展springmvc dispatchservlet
//用于拓展SPRINGMVC
@Configuration
//这玩意就是导入了一个类：从容器中获取所有的WEBMVCCONFIG:
public class MyMvcConfig implements WebMvcConfigurer {
    //public interface ViewResolver 实现了视图解析器的类，我们就可以把它看做视图解析器
//还没学springboot整合mabatis啊怎么用数据库？
    //试图跳转

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }
    //放到BEAN里面自定义国际化组件才生效
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
//拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/css/*","/js/**","/img/**");

    }
}
