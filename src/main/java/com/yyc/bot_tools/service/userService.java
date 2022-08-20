package com.yyc.bot_tools.service;

import com.yyc.bot_tools.entities.User;

public interface userService {

    User selectUser(User user);

    User selectUserByUserName(String userName);

}
