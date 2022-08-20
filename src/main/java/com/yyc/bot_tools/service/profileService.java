package com.yyc.bot_tools.service;

import com.yyc.bot_tools.entities.Profile;

import java.util.List;

public interface profileService {

    int addProfile(Profile profile);

    List<Profile> selectAllProfile(int pageNum , int pageSize);

    int updateProfile(Profile profile);

    Profile selectProfileById(Integer id);

    int deleteProfileById(Integer id);

    int countProfileNum();

    int batchDeleteProfile(int[] idList);

    int batchUpdateProfile(int[] idList,Profile profile);

}
