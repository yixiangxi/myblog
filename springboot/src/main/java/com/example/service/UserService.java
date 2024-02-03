/**
 * 功能：
 * 作者：yixiangxi
 * 日期：2024/2/3 16:38
 **/
package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Admin;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    public void add(User user) {

        //业务方法
//        1.判断账户是否重复
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if(dbUser != null){
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
//        2.判断密码是否为空
        if(ObjectUtil.isEmpty(user.getPassword())){
            user.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
//        3.判断用户名称是否为空
        if(ObjectUtil.isEmpty((user.getName()))){
            user.setName(user.getUsername());
        }
//        4.设置用户角色
        user.setRole(RoleEnum.USER.name());;
        userMapper.insert(user);

    }

    public void deleteById(Integer id) {userMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for(Integer id:ids){
            userMapper.deleteById(id);
        }
    }

    public void updateById(User user) {
        userMapper.updateById(user);
    }

    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }

    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userMapper.selectAll(user);
        return PageInfo.of(userList);
    }
}
