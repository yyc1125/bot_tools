package com.yyc.bot_tools.controller;

import com.yyc.bot_tools.entities.Profile;
import com.yyc.bot_tools.service.profileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
@RequestMapping("/profile")
public class profileController {

    @Autowired
    profileService profileService;

    @GetMapping("billing")
    public String addProfile(Profile profile) {
        String result;
        int i = profileService.addProfile(profile);
        if (i > 0) {
            result = "success";
        } else {
            result = "fail";
        }

        return result;
    }

}
