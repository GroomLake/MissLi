package com.consum.demo.contro;

import com.consum.demo.service.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MissLi
 * @create 2019/4/22 22:27
 */
@RestController
public class Testcontrol {
    @Autowired
    private Test test;
    @RequestMapping(value = "/testDemo",method = RequestMethod.GET)
    public String testDemo(String name){
        System.out.println("消费者 "+name);
        return test.test(name);
    }
}
