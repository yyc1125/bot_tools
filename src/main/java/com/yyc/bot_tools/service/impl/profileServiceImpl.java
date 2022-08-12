package com.yyc.bot_tools.service.impl;

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
    public List<Profile> selectAllProfile() {
        return profileMapper.selectAllProfile();
    }
}
