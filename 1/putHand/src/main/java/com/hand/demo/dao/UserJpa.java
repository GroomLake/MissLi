package com.hand.demo.dao;

import com.hand.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author MissLi
 * @create 2019/4/5 20:15
 */
public interface UserJpa extends JpaRepository<User,Integer> {
    User findUserByUserNameAndPassword(String name,String pwd);
    List<User> findAllByRole(String role);
    List<User> getAllBy();
    User findUserByUserName(String name);
}
