package com.yyc.bot_tools.controller;

import com.yyc.bot_tools.entities.Param;
import com.yyc.bot_tools.entities.Profile;
import com.yyc.bot_tools.entities.Result;
import com.yyc.bot_tools.service.profileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@ResponseBody
@Controller
@RequestMapping("/profile")
public class profileController {

    @Autowired
    profileService profileService;

    @PostMapping("/addProfile")
    public Result addProfile(@RequestBody Profile profile) {
        int i = profileService.addProfile(profile);
        if (i > 0) {
            return new Result(200, "添加成功");
        } else {
            return new Result(400, "添加失败");
        }
    }

    @GetMapping("/selectAllProfile")
    public Result selectAllProfile(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        List<Profile> profiles = profileService.selectAllProfile(pageNum, pageSize);
        int size = profileService.countProfileNum();
        return new Result(profiles, size);
    }

    @PostMapping("/updateProfile")
    public Result updateProfile(@RequestBody Param param) {
        Profile profile = param.getProfile();
        String[] checkboxGroup = param.getCheckboxGroup();
        int i = profileService.updateProfile(profile,checkboxGroup);
        if (i > 0) {
            return new Result(200, "更新成功");
        } else {
            return new Result(400, "更新失败");
        }
    }

    @GetMapping("/selectProfileById")
    public Result selectProfileById(@RequestParam("id") Integer id) {
        return new Result(profileService.selectProfileById(id));
    }

    @GetMapping("/deleteProfileById")
    public Result deleteProfileById(@RequestParam("id") Integer id) {
        int i = profileService.deleteProfileById(id);
        if (i > 0) {
            return new Result(200, "删除成功");
        } else {
            return new Result(400, "删除失败");
        }
    }

    @PostMapping("/batchDeleteProfile")
    @Transactional
    public Result batchDeleteProfile(@RequestBody int[] idList) {
        int i = profileService.batchDeleteProfile(idList);
        if (i > 0) {
            return new Result(200, "删除成功");
        } else {
            return new Result(400, "删除失败");
        }
    }

    @PostMapping("/batchUpdateProfile")
    @Transactional
    public Result batchUpdateProfile(@RequestBody Param param) {
        int[] idList = param.getIdList();
        Profile profile = param.getProfile();
        String[] checkboxGroup = param.getCheckboxGroup();
        int i = profileService.batchUpdateProfile(idList, profile,checkboxGroup);
        if (i > 0) {
            return new Result(200, "修改成功");
        } else {
            return new Result(400, "修改失败");
        }
    }

//    @GetMapping(value = "/exportProfile")
//    public Result exportProfile(HttpServletResponse response,
//                             @RequestParam("id") int[] idList) {
//        Profile profile = profileService.selectProfileById(idList); //根据具体业务定义
//        return profileService.exportUser(response, profile);
//    }

    @PostMapping(value = "/exportProfile")
    public Result exportProfile(HttpServletResponse response,
                             @RequestBody int[] idList) {
         //根据具体业务定义
        return profileService.exportProfile(response, idList);
    }

    @PostMapping(value = "/importProfile")
    public Result importProfile(@RequestParam("file") MultipartFile multipartFile) {
        //根据具体业务定义
        return profileService.importProfile(multipartFile);
    }
}
