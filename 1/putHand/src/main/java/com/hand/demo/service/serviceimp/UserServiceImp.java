package com.hand.demo.service.serviceimp;

import com.hand.demo.dao.UserDao;
import com.hand.demo.dao.UserJpa;
import com.hand.demo.pojo.User;
import com.hand.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MissLi
 * @create 2019/4/5 20:17
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserJpa userJpa;
    @Autowired
    private UserDao userDao;
    @Override
    public User findUserByUserNameAndPassword(String name, String pwd) {
        return userJpa.findUserByUserNameAndPassword(name,pwd);
    }

    @Override
    public List<User> findAllByRole(String role) {
        return userJpa.findAllByRole(role);
    }

    @Override
    public List<User> getAllBy() {
        return userJpa.getAllBy();
    }

    @Override
    public boolean findUserByUserName(String name) {
        User user=userJpa.findUserByUserName(name);
        if(user!=null){
            return true;
        }
        return false;
    }

    @Override
    public void updateUserthroughPassword(String name, String pwd) {
        userDao.updateUserByUserNameAndPassword(name,pwd);
    }

    @Override
    public void saveUser(User user) {
        userJpa.save(user);
    }
}
