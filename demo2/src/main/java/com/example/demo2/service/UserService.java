package com.example.demo2.service;

import com.example.demo2.bean.Tuser;

public interface UserService {
    boolean login(Tuser u);

    boolean regist(Tuser u);
}
