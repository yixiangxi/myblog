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
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

}
