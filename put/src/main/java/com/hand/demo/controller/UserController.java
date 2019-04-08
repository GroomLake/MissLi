package com.hand.demo.controller;

import com.hand.demo.model.MyWebSocket;
import com.hand.demo.model.UserModel;
import com.hand.demo.pojo.FinalStatic;
import com.hand.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author MissLi
 * @create 2019/4/5 20:19
 */
@Controller
public class UserController {
    @Autowired
    private UserModel userModel;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String user(){
        return "user";
    }
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String admin(){
        return "admin";
    }
    @PostMapping(value = "/index")
    public ModelAndView userData(User user,HttpServletRequest request){
        HttpSession session=request.getSession();
        ModelAndView view;
        /**
         * 二次验证
         */
        User user1=userModel.getUserByUserNameAndId(user.getUserName(),user.getPassword());
        if("admin".equals(user1.getRole().toLowerCase())){
            view=new ModelAndView("admin");
            view.addObject("listUser",userModel.getListUser());
        }else{
            view=new ModelAndView("user");
        }
        session.setAttribute(FinalStatic.USER.toString(),user1);
        MyWebSocket.setHttpSession(session);
        view.addObject("user",user1);
        return view;
    }
}
