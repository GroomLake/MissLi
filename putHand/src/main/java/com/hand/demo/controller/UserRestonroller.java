package com.hand.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.demo.model.MyWebSocket;
import com.hand.demo.pojo.Advice;
import com.hand.demo.pojo.User;
import com.hand.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MissLi
 * @create 2019/4/5 20:20
 */
@RestController
public class UserRestonroller {
    @Autowired
    private UserService userService;
    /**
     * 判断用户姓名和密码
     * @param request
     * @return
     */
    @PostMapping(value = "/deal/userData")
    public String dealData(HttpServletRequest request){
        String userName=request.getParameter("userName");
        String pwd=request.getParameter("password");
        User user=userService.findUserByUserNameAndPassword(userName,pwd);
        if(user==null){
            return "false";
        }
        return "true";
    }
    @PostMapping(value = "/online/people")
    public String onlinePeople() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        List<Advice> list=new ArrayList<>(MyWebSocket.hashSet);
        return objectMapper.writeValueAsString(list);
    }
}
