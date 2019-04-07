package com.hand.demo.model;

import com.hand.demo.pojo.User;
import com.hand.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MissLi
 * @create 2019/4/7 9:49
 */
@Component
public class UserModel {
    @Autowired
    private UserService userService;
    public List<User> getListUser(){
        return userService.findAllByRole("user");
    }
    public User getUserByUserNameAndId(String name,String id){
        return userService.findUserByUserNameAndPassword(name,id);
    }
}
