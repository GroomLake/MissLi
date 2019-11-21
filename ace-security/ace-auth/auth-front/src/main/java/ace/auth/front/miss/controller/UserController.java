package ace.auth.front.miss.controller;

import ace.auth.front.miss.model.TokenUtil;
import com.miss.pojo.User;
import com.miss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author MissLi
 * @create 2019/4/23 19:52
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenUtil tokenUtil;
    @PostMapping(value = "/judgeName")
    private @ResponseBody String judgeName(@RequestParam("name") String name){
        return String.valueOf(userService.findUserByName(name));
    }

    @PostMapping(value = "/login")
    private @ResponseBody String login(@RequestParam("userName") String userName,
                                       @RequestParam("password") String password){
        boolean flag=userService.findUserByNameAndPassword(userName,password);
        //此处还要返回token
        if(flag) {
            String token = tokenUtil.getTokenResult(userName);
            System.out.println("token:" + token);
        }
        return String.valueOf(flag);
    }
    @PostMapping(value = "/register")
    private @ResponseBody String saveUser(@RequestParam("user")User user){
        return String.valueOf(userService.saveUser(user));
    }
    @PostMapping(value = "/updateUser")
    private @ResponseBody String updateUser(@RequestParam(value = "user")User user){
        return String.valueOf(userService.updateUserByname(user));
    }
}
