package com.filter;
import com.tool.Base64;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CORSFilter",urlPatterns = "/*")
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request =  (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String origin = request.getHeader("Origin");
        System.out.println(origin);
        response.setHeader("Access-Control-Allow-Origin",origin);

        //把sessionID通过cookie传给客户端。 同时设置samesite=none,解除浏览器限制。
        //不能让tomcat以默认的方式设置sessionID到cookie中，然后发给客户端，sessionid会悲剧。 因为默认方式没有解除浏览器限制。
        String id = request.getSession().getId().toString();
        response.setHeader("Set-Cookie", "JSESSIONID="+id+";HttpOnly;Secure;SameSite=None");



        System.out.println("session id:"+id);

//        try {
//
//            response.setHeader("Set-Cookie", "SESSION="+ Base64.encryptBASE64(id)+";HttpOnly;Secure;SameSite=None");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //允许第三方携带cookie
        //是否支持cookie跨域。如果不加，Jquery的xhrFields就会报错。
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization,Origin, X-Requested-With, Content-Type, Accept,Access-Token");//Origin, X-Requested-With, Content-Type, Accept,Access-Token
        response.setHeader("Access-Control-Allow-Methods", "*");

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
