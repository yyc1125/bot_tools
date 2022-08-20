package com.yyc.bot_tools.dao;

import com.yyc.bot_tools.entities.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userMapper {
    User selectUser(User user);

    User selectUserByUserName(String userName);

}
