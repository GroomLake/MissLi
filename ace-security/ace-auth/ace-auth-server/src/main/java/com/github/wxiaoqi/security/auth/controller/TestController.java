package com.github.wxiaoqi.security.auth.controller;

import com.github.wxiaoqi.security.auth.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MissLi
 * @create 2019/4/22 13:30
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping(value = "/testName")
    public String testName(@RequestParam(value = "name") String name){
        System.out.println("服务提供者 "+name);
        return testService.sayName(name);
    }
}
