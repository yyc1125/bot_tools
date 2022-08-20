package com.yyc.bot_tools.dao;

import com.yyc.bot_tools.entities.Profile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface profileMapper {

    int addProfile(Profile profile);

    List<Profile> selectAllProfile();

    int updateProfile(Profile profile);

    Profile selectProfileById(Integer id);

    int deleteProfileById(Integer id);

    int countProfileNum();

}
