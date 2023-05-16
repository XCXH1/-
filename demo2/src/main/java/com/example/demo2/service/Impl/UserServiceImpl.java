package com.example.demo2.service.Impl;

import com.example.demo2.bean.Tuser;
import com.example.demo2.mapper.UserMapper;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean login(Tuser u) {
        int num = userMapper.login(u);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean regist(Tuser u) {
        int num = userMapper.regist(u);
        if(num>0){
            return true;
        }
        return false;
    }
}
