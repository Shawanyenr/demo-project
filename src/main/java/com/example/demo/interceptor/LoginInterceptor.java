package com.example.demo.interceptor;

import com.example.demo.po.User;
import com.example.demo.service.UserDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserDaoService userDaoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
//        System.out.println(url);
        System.out.println("请求: " + url);


        if (url.contains("/admin")){
            if (request.getSession().getAttribute("admin") == null) {
                response.sendRedirect("/admin/login");
                return false;
            }
        }else{
            if (request.getSession().getAttribute("user")==null){
                response.sendRedirect("/login");
                return false;
            }else {
                User user = (User) request.getSession().getAttribute("user");
                if (user.getFrozeUntil() != null) {
                    if (!(user.getFrozeUntil().before(new Date()))) {
                        response.sendRedirect("/login");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
