package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.LoginVO;
import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.enums.ResultCode;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * createdBy: weishixin
 * date: 2018/01/04
 */
@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserService userService;


    /**
     * 检查用户是否是已注册系统用户
     * @param email
     * @param pwd
     * @return
     */
    @PostMapping("/checklogin")
    public ResultVO<User> checkLogin(@RequestParam String email , @RequestParam String pwd){
        User result = userService.findByEmailAndPsw(email,pwd);
        if( result == null ){
            ResultVO<User> resultVO = new ResultVO<>();
            resultVO.setCode(ResultCode.NOT_FOUND.getCode());
            resultVO.setMessage("用户名或密码错误");
            System.out.println(resultVO.getCode());
            return resultVO;
        }else {
            ResultVO<User> resultVO = new ResultVO<>(result);
            System.out.println(resultVO.getCode());
            return resultVO;
        }
    }


}
