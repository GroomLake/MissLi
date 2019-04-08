package com.hand.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author MissLi
 * @create 2019/4/8 21:48
 */
@Mapper
@Repository
public interface UserDao {
    void updateUserByUserNameAndPassword(String name, String pwd);
}
