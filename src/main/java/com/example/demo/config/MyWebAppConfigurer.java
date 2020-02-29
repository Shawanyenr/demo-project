package com.example.demo.config;

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
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        String[] addPathPatterns = {
//                "/**"
//        };
//        String[] excludePathPatterns = {
//                "/test",
//                "/static",
//                "/login.action",
//                "/register.action",
//                "/loadMore"
//        };
////        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
//        registry.addInterceptor(new LoginInterceptor());
//    }
}