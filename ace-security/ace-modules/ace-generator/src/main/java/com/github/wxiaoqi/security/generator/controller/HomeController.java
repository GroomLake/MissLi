package com.github.wxiaoqi.security.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-06 13:34
 */
@Controller
public class HomeController{


    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(Map<String,Object> map){
        return "index";
    }

    @RequestMapping(value = "about",method = RequestMethod.GET)
    public String about(){
        return "about";
    }
    @RequestMapping(value = "generator",method = RequestMethod.GET)
    public String user(){
        return "generator/list";
    }

    @GetMapping("/test")
    public @ResponseBody String test(){
        return "success!!!!";
    }
}
