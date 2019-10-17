package com.cy.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())     //将配置的登陆拦截器注册进来
                .addPathPatterns("/admin/**")       //对/admin路径下所有请求进行拦截
                .excludePathPatterns("/admin")      //对着个路径不拦截
                .excludePathPatterns("/admin/login");

    }
}
