package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.enums.ResultCode;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * createdBy: weishixin
 * date: 2018/01/06
 */
@RestController
@RequestMapping("/")
public class RegisterController {
    @Autowired
    private UserService userService;

    /**
     * 检查用户输入的邮箱是否已经被注册
     * @param emailname
     * @return
     */
    @GetMapping("/checkemail")
    public ResultVO<User> checkEmail(@RequestParam String emailname){
        User userInfoInDB = userService.findByEmail(emailname);
        if (userInfoInDB==null){
            ResultVO<User> resultVO = new ResultVO<>();
            resultVO.setCode(ResultCode.NOT_FOUND.getCode());
            resultVO.setMessage("该邮箱尚未注册");
            System.out.println(resultVO.getCode());
            return resultVO;
        }else {
            System.out.println("该用户名字为："+userInfoInDB.getUsername());
            ResultVO<User> resultVO = new ResultVO<>(userInfoInDB);
            System.out.println(resultVO.getCode());
            return resultVO;
        }
    }
}
