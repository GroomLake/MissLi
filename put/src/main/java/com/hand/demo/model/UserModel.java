package com.hand.demo.model;

import com.hand.demo.pojo.FinalStatic;
import com.hand.demo.pojo.User;
import com.hand.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate redisTemplate;
    public List<User> getListUser(){
        List<User> userList=redisTemplate.opsForList().range(FinalStatic.ALLUSER.toString(),0,-1);
        for (User user:userList){
            if("admin".equals(user.getRole().toLowerCase())){
                userList.remove(user);
            }
        }
        if(userList.size()==0){
            userList=userService.findAllByRole("user");
        }
        return userList;
    }
    public User getUserByUserNameAndId(String name,String id){
        List<User> userList=redisTemplate.opsForList().range(FinalStatic.ALLUSER.toString(),0,-1);
        User getUser=null;
        for (User user:userList){
            if(user.getUserName().equals(name)&&user.getPassword().equals(id)){
                getUser=user;
                break;
            }
        }
        if(getUser==null){
            getUser=userService.findUserByUserNameAndPassword(name,id);
        }
        return getUser;
    }
}
