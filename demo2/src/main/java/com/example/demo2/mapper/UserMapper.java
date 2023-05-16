package com.example.demo2.mapper;

import com.example.demo2.bean.Tuser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int login(Tuser u);

    int regist(Tuser u);
}
