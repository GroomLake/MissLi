package com.miss.service;

import com.miss.pojo.User;

/**
 * @author MissLi
 * @create 2019/4/23 19:28
 * 与用户名相关的服务接口
 */
public interface UserService {
    boolean findUserByName(String name);
    boolean findUserByNameAndPassword(String name,String password);
    boolean saveUser(User user);
    boolean updateUserByname(User user);
}
