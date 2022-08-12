package com.yyc.bot_tools.service;

import com.yyc.bot_tools.entities.Profile;

import java.util.List;

public interface profileService {

    int addProfile(Profile profile);

    List<Profile> selectAllProfile();
}
