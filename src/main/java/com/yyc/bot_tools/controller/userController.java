package com.yyc.bot_tools.controller;

import com.alibaba.fastjson.JSONObject;
import com.yyc.bot_tools.Util.TokenUtil;
import com.yyc.bot_tools.entities.Result;
import com.yyc.bot_tools.entities.User;
import com.yyc.bot_tools.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {

        //数据库进行检查
        User userResult = userService.selectUser(user);

        JSONObject result = new JSONObject();
        //若正确返回200，若错误返回400
        if (userResult == null) {
            result.put("code",200);
            return new Result(400,"登陆失败");
        } else {
            String token = TokenUtil.sign(user.getUserName());
            return new Result(token);
        }
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestParam("token") String token){
        User verifyUser = TokenUtil.verify(token);
        if (verifyUser != null) {
            return new Result(userService.selectUserByUserName(verifyUser.getUserName()));
        }else {
            return new Result(400,"token已过期");
        }
    }
}
