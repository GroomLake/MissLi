package com.hand.demo.model;

import com.hand.demo.dao.UserDao;
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
    @Autowired
    private UserDao userDao;
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
    public boolean findUserByName(String name){
        List<User> userList=redisTemplate.opsForList().range(FinalStatic.ALLUSER.toString(),0,-1);
        for (User user:userList){
            if(user.getUserName().equals(name)){
                return true;
            }
        }
        return userService.findUserByUserName(name);
    }
    public void updatePasswordByName(User user){
        userDao.updateUserByUserNameAndPassword(user.getUserName(),user.getPassword());
    }
    public void saveUser(User user){
        redisTemplate.opsForList().leftPush(FinalStatic.ALLUSER.toString(),user);
        try {
            userService.saveUser(user);
        }catch (Exception e){
            System.out.println("用户保存失败!"+user);
        }

    }
}
