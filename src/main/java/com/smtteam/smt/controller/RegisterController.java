package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.Constants;
import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.enums.ResultCode;
import com.smtteam.smt.common.exception.ExistException;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.UserService;
import com.smtteam.smt.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
            System.out.println(resultVO.getCode());
            return resultVO;
        }else {
            System.out.println("该用户名字为："+userInfoInDB.getUsername());
            ResultVO<User> resultVO = new ResultVO<>(userInfoInDB);
            System.out.println(resultVO.getCode());
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
            System.out.println(user.getEmail()+" username:"+user.getUsername()+" userstatus:"+user.getStatus());
            return new ResultVO<>(user);
        }catch (ExistException e){
            e.printStackTrace();
            return new ResultVO<>(e.getMessage());
        }

    }

    /**
     * 用户在邮箱中接受验证，完成注册邮箱的验证
     * @param code
     * @return ModelAndView 实现点击验证链接，自动跳转
     */
    @GetMapping("/acceptverifyemail/{code}")
    public ModelAndView acceptVerifyEmail(@PathVariable String code){
        System.out.println("验证邮件里的url后的code："+code);
        if(code == null || code.isEmpty()){
            return new ModelAndView("redirect:/login");//重定向到login界面
        }
        User user = null;
        try {
            user = verifyService.acceptVerifyEmail(code);

        }catch (NoAccessException e){
//            return new ResultVO<>(e.getMessage());
            return new ModelAndView("redirect:/login");//重定向到login界面
        }
//        return new ResultVO<>(user);
        ModelAndView modelAndView = new ModelAndView("redirect:/main");//验证成功登录进主界面
        modelAndView.addObject("userid",user.getId());//将userid传到主界面
        return modelAndView;
    }
}
