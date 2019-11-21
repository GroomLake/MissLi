package com.hand.demo.service;

import com.hand.demo.pojo.User;

import java.util.List;

/**
 * @author MissLi
 * @create 2019/4/5 20:17
 */
public interface UserService {
    User findUserByUserNameAndPassword(String name, String pwd);
    List<User> findAllByRole(String role);
    List<User> getAllBy();
}
