package com.Interceptor;

import com.alibaba.fastjson.JSON;
import com.pojo.RE;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        System.out.println(url+"==="+userName);
        if (url.contains("login")){
            return true;
        }else {
            if (userName!=null){
                return true;
            }else {
                RE re = new RE(3,"未登录或恶意访问",null);
                String res = JSON.toJSONString(re);
                System.out.println("fastjson恶意访问"+res);
                PrintWriter writer = response.getWriter();
                writer.write(res);
                writer.flush();

                return false;
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
