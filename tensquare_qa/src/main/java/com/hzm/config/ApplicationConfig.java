package com.hzm.config;

import com.hzm.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class ApplicationConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtFilter jwtFilter;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtFilter)
                .addPathPatterns("/**")//过滤所有的请求
                .excludePathPatterns("/**/login");//除了登录
    }
}
