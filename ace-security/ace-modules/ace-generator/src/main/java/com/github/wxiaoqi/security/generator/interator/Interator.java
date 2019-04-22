package com.github.wxiaoqi.security.generator.interator;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MissLi
 * @create 2019/4/21 11:41
 */
public class Interator implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("*****"+request.getRequestURI()+request.getServerPort());
        return true;
    }
}
