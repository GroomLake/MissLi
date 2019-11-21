package com.hand.demo.interacter;

import com.hand.demo.pojo.FinalStatic;
import com.hand.demo.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MissLi
 * @create 2019/4/7 19:58
 */
public class LoginInteracter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user= (User) request.getSession().getAttribute(FinalStatic.USER.toString());
        if(request.getRequestURI().equals("/admin")||request.getRequestURI().equals("/user")){
            if(user!=null){
                return true;
            }else{
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }
}
