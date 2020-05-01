package com.example.demo.config;

import com.example.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/post_img/**").addResourceLocations("file:D:/00webappresources/user_upload/post_img/");
        registry.addResourceHandler("/user_avatar/**").addResourceLocations("file:D:/00webappresources/user_upload/user_avatar/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] addPathPatterns = {
                "/admin/**",
                "/delete_post_id/**",
                "/update_post_id/**",
                "/uploadForm/**",
                "/toggleSub/**",
                "/subscription/**",
                "/message/**"
        };
        String[] excludePathPatterns = {
                "/admin/login",
                "/admin/login.action",
                "/login.action",
                "/register.action",
                "/login",
                "/register",

        };
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
//        registry.addInterceptor(new LoginInterceptor());
    }
}