package com.example.mapper;

import com.example.entity.Admin;
import com.example.entity.User;

import javax.annotation.Resource;

public interface UserMapper {
    /**
     * 新增
     */
    int insert(User user);

    User selectByUsername(String username);



}
