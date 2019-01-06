package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * createdBy: weishixin
 * date: 2018/01/02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 新增用户
     * @param email
     * @param psw
     * @param username
     * @param gender
     * @param verify 验证信息
     * @return
     */
    @PostMapping("/add")
    public ResultVO<User> addUser(@RequestParam String email, @RequestParam String psw, @RequestParam String username, @RequestParam int gender, @RequestParam String verify ){
        User user = new User( email,psw, username, gender, verify);
        User result = userService.addUser(user);
        return new ResultVO<>(result);
    }


}
