package com.consum.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MissLi
 * @create 2019/4/22 22:25
 */
@FeignClient(value = "ace-auth")
public interface Test {
    @GetMapping("/testName")
    String test(@RequestParam("name") String name);
}
