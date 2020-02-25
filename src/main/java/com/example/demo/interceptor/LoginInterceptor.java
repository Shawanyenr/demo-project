package com.example.demo.interceptor;

import com.example.demo.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
//        System.out.println(url);
        System.out.println("拦截器......url:" + url);


        if (url.contains("/login.action") || url.contains("/register.action") || url.contains("/new_home") || url.contains("/static") || url.contains("/loadMore") || url.contains("/post_img") || url.contains("/error")){
//            System.out.println(url);
            return true;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
//            request.logout();
//            response.sendRedirect("/test");
            return true;
        }
        response.sendRedirect("/new_home");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
