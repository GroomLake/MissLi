package com.github.wxiaoqi.security.auth.service.impl;

import com.github.wxiaoqi.security.auth.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author MissLi
 * @create 2019/4/22 9:21
 */
@Service
public class TestServiceImp implements TestService {
    @Override
    public String sayName(String name) {
        return "hello "+name;
    }
}
