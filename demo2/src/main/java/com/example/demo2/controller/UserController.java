package com.example.demo2.controller;

import com.example.demo2.bean.Tuser;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(Tuser u){
        boolean f = userService.login(u);
        if(f){
            return "redirect:index";
        }
        return "login";
    }

    @RequestMapping("/regist")
    public String regist(Tuser u){
        boolean f = userService.regist(u);
        if(f){
            return "redirect:login";
        }
        return "regist";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}


