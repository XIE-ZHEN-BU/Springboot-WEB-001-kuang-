package com.kuang.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
//实现了implement接口后要重写方法
public class MyLocalResolver implements LocaleResolver {

//解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest request ){
//获取请求中的语言参数
        String language=request.getParameter("l");
        System.out.println("Debug==>"+language);
        Locale locale = Locale.getDefault();//如果没有就使用默认的
        //如果请求的链接携带了国际化的参数
        if(!StringUtils.isEmpty(language)){
            //zh_CN
            String[] split = language.split("_");
            //国家，地区
           locale =  new Locale(split[0],split[1]);

        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
