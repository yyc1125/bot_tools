package com.yyc.bot_tools.service.impl;

import com.yyc.bot_tools.dao.userMapper;
import com.yyc.bot_tools.entities.User;
import com.yyc.bot_tools.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService{

    @Autowired
    private userMapper userMapper;

    @Override
    public User selectUser(User user) {
        return userMapper.selectUser(user);
    }

    @Override
    public User selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }
}
