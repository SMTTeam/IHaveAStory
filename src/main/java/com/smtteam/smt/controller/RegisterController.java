package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.common.enums.ResultCode;
import com.smtteam.smt.common.exception.ExistException;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.UserService;
import com.smtteam.smt.service.VerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * createdBy: weishixin
 * date: 2018/01/06
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private VerifyService verifyService;

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    /**
     * 检查用户输入的邮箱是否已经被注册（js中ajax请求）
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
            String infoCode = String.valueOf(ResultCode.NOT_FOUND.getCode());
            logger.info(infoCode);
            return resultVO;
        }else {
            String loginfo = "该用户名字为："+userInfoInDB.getUsername();
            logger.info(loginfo);
            ResultVO<User> resultVO = new ResultVO<>(userInfoInDB);
            String loginfoCode = String.valueOf(resultVO.getCode());
            logger.info(loginfoCode);
            return resultVO;
        }
    }

    /**
     * 注册页面点击表单提交，发送验证邮件到用户邮箱
     * @param email
     * @param username
     * @param psw
     * @return
     */
    @PostMapping("/verifyemail")
    public ResultVO<User> sendVerifyEmail(@RequestParam String email,@RequestParam String username, @RequestParam String psw){

        User user = null;
        try {
            user = verifyService.sendVerifyEmail(email, psw, username);
            String loginfo = user.getEmail()+" username:"+user.getUsername()+" userstatus:"+user.getStatus();
            logger.info(loginfo);
            return new ResultVO<>(user);
        }catch (ExistException e){
            logger.info(e.getMessage());
            return new ResultVO<>(e.getMessage());
        }

    }

    /**
     * 用户在邮箱中接受验证，完成注册邮箱的验证
     * @param code
     * @return ModelAndView 实现点击验证链接，自动跳转
     */
    @GetMapping("/acceptverifyemail/{code}")
    public ModelAndView acceptVerifyEmail(@PathVariable String code, HttpServletRequest request){
        String loginfo = "验证邮件里的url后的code："+code;
        logger.info(loginfo);

        User user = null;
        try {
            user = verifyService.acceptVerifyEmail(code);
        }catch (NoAccessException e){
            return new ModelAndView("redirect:/login");//重定向到login界面
        }
        HttpSession session = request.getSession();
        ShowUser showUser = new ShowUser();
        showUser.setEmail(user.getEmail());
        showUser.setUsername(user.getUsername());
        showUser.setId(user.getId());
        session.setAttribute("user",showUser);
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("redirect:/");//验证成功登录进主界面
        return modelAndView;
    }
}
