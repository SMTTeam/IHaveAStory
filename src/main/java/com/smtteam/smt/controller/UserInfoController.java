package com.smtteam.smt.controller;


import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.common.enums.ResultCode;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * created by weishixin
 * date: 2019-01-09
 */
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @PostMapping("/update")
    public ResultVO<Integer> updateInfo(@RequestParam String useremail, @RequestParam String username , HttpServletRequest request){
        User user = userService.findByEmail(useremail);
        if( user == null ){
            ResultVO<Integer> resultVO = new ResultVO<>();
            resultVO.setCode(ResultCode.NOT_FOUND.getCode());
            resultVO.setMessage("更新用户不存在");
            return resultVO;
        }else {
            int userResult = userService.updateUserInfoByEmail(useremail,username);
            ResultVO<Integer> resultVO = new ResultVO<>(userResult);
            /*更新session中的user对象*/
            ShowUser showUser = new ShowUser();
            showUser.setEmail(user.getEmail());
            showUser.setUsername(user.getUsername());
            HttpSession session = request.getSession();
            session.setAttribute("user",showUser);

            String loginfoCode = String.valueOf(resultVO.getCode());
            logger.info(loginfoCode);
            return resultVO;
        }
    }

    /**
     * 根据用户输入的旧密码，验证其旧密码是否正确
     * @param oldpsw 旧密码
     * @param useremail 用户邮箱
     * @return
     */
    @GetMapping("/checkoldpsw")
    public ResultVO<ShowUser> checkOldPsw (@RequestParam String oldpsw , @RequestParam String useremail ){
        User user = userService.findByEmailAndPsw(useremail,oldpsw);
        if( user == null ){
            ResultVO<ShowUser> resultVO = new ResultVO<>();
            resultVO.setCode(ResultCode.NOT_FOUND.getCode());
            resultVO.setMessage("密码不对，查找失败");
            String infoCode = String.valueOf(resultVO.getCode());
            logger.info(infoCode);
            return resultVO;
        }else {
            ShowUser showUser = new ShowUser();
            showUser.setId(user.getId());
            showUser.setUsername(user.getUsername());
            showUser.setEmail(user.getEmail());
            ResultVO<ShowUser> resultVO = new ResultVO<>(showUser);
            String checkOldPswCode = String.valueOf(resultVO.getCode());
            logger.info(checkOldPswCode);
            return resultVO;
        }
    }

    @PostMapping("/changepsw")
    public ResultVO<Integer> changePsw(@RequestParam String useremail, @RequestParam String newpsw){
        User user = userService.findByEmail(useremail);
        if( user == null ){
            ResultVO<Integer> resultVO = new ResultVO<>();
            resultVO.setCode(ResultCode.NOT_FOUND.getCode());
            resultVO.setMessage("更新用户不存在");
            return resultVO;
        }else {
            int userResult = userService.updateUserPswByEmail(useremail,newpsw);
            ResultVO<Integer> resultVO = new ResultVO<>(userResult);

            String loginfoCode = String.valueOf(resultVO.getCode());
            logger.info(loginfoCode);
            return resultVO;
        }
    }

}
