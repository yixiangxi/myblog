package com.example.mapper;

import com.example.entity.User;

import java.util.List;

public interface UserMapper {
    /**
     * 新增
     */
    int insert(User user);

    User selectByUsername(String username);


   int deleteById(Integer id);

    int updateById(User user);

    User selectById(Integer id);

    List<User> selectAll(User user);
}
