package com.hand.demo.interacter;

import com.hand.demo.pojo.FinalStatic;
import com.hand.demo.pojo.User;
import com.hand.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author MissLi
 * @create 2019/4/8 16:14
 */
@WebListener
public class SystemListener implements ServletContextListener {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.delete(FinalStatic.ALLUSER.toString());
        List<User> userList=userService.getAllBy();
        if(userList!=null){
            redisTemplate.opsForList().leftPushAll(FinalStatic.ALLUSER.toString(),userList);
            System.out.println("all data:"+userList);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        redisTemplate.delete(FinalStatic.ALLUSER.toString());
    }
}
