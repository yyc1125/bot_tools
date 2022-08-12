package com.yyc.bot_tools.dao;

import com.yyc.bot_tools.entities.Profile;

import java.util.List;

public interface profileMapper {

    int addProfile(Profile profile);

    List<Profile> selectAllProfile();
}
