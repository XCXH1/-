package com.example.demo2.controller;

import com.example.demo2.util.Base64Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/regist")
    public String regist(){
        return "regist";
    }
    @RequestMapping("/upload")
    @ResponseBody   // 有ajax就需要用到
    public Map<String,Object> upload(MultipartFile file){
        String base64 = Base64Util.converToBase64(file);
        Map<String,Object> map = new HashMap<>();
        map.put("img",base64);
        return map;
    }
}
