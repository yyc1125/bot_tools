package com.yyc.bot_tools.service;

import com.yyc.bot_tools.entities.Profile;
import com.yyc.bot_tools.entities.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface profileService {

    int addProfile(Profile profile);

    List<Profile> selectAllProfile(int pageNum, int pageSize);

    int updateProfile(Profile profile,String[] checkboxGroup);

    Profile selectProfileById(Integer id);

    int deleteProfileById(Integer id);

    int countProfileNum();

    int batchDeleteProfile(int[] idList);

    int batchUpdateProfile(int[] idList, Profile profile,String[] checkboxGroup);

//    Result exportUser(HttpServletResponse response, Profile profile);

    Result exportProfile(HttpServletResponse response, int[] idList);

    Result importProfile(MultipartFile multipartFile);
}
