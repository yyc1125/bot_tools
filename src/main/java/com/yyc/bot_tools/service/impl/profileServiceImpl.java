package com.yyc.bot_tools.service.impl;

import com.github.pagehelper.PageHelper;
import com.yyc.bot_tools.dao.profileMapper;
import com.yyc.bot_tools.entities.Profile;
import com.yyc.bot_tools.service.profileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class profileServiceImpl implements profileService {

    @Autowired
    private profileMapper profileMapper;

    @Override
    public int addProfile(Profile profile) {
        return profileMapper.addProfile(profile);
    }

    @Override
    public List<Profile> selectAllProfile(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return profileMapper.selectAllProfile();
    }

    @Override
    public int updateProfile(Profile profile) {
        return profileMapper.updateProfile(profile);
    }

    @Override
    public Profile selectProfileById(Integer id) {
        return profileMapper.selectProfileById(id);
    }

    @Override
    public int deleteProfileById(Integer id) {
        return profileMapper.deleteProfileById(id);
    }

    @Override
    public int countProfileNum() {
        return profileMapper.countProfileNum();
    }

    @Override
    public int batchDeleteProfile(int[] idList) {
        int count = 0;
        for (int i = 0; i < idList.length; i++) {
            count = count + profileMapper.deleteProfileById(idList[i]);
        }
        return count;
    }

    @Override
    public int batchUpdateProfile(int[] idList , Profile profile) {
        int count = 0;
        for (int i = 0; i < idList.length; i++) {
            profile.setId(idList[i]);
            count = count + profileMapper.updateProfile(profile);
        }
        return count;
    }

}
