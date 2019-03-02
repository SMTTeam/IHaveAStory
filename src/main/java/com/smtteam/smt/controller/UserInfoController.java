package com.smtteam.smt.controller;


import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.common.enums.ResultCode;
import com.smtteam.smt.common.exception.NotExistException;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

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

    /**
     * 发送重置密码邮件到用户邮箱
     * @param useremail
     * @return
     */
    @PostMapping("/sendresetpswemail")
    public ResultVO<User> sendResetPswEmail(@RequestParam String useremail){
        User user = null;
        try {
            user = userService.sendResetPswEmail(useremail);
            String loginfo = user.getEmail()+" username:"+user.getUsername();
            logger.info(loginfo);
            return new ResultVO<>(user);
        }catch (NotExistException e){
            logger.info(e.getMessage());
            return new ResultVO<>(e.getMessage());
        }
    }

    @GetMapping("/resetpsw/{code}")
    public ModelAndView resetPsw(@PathVariable String code, HttpServletRequest request){
        String loginfo = "重置密码邮件里的url后的code："+code;
        logger.info(loginfo);

        byte[] bytes = Base64.getDecoder().decode(code);
        String source = new String(bytes, StandardCharsets.UTF_8);
        String[] array = source.split("&");
        String email = array[0];
        String send_time = array[1];
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String current_time = df.format(new Date());
        logger.info("点击链接的时间是："+current_time);
        try {
            Date sendTime = df.parse(send_time);
            Date currentTime = df.parse(current_time);
            long diff = sendTime.getTime() - currentTime.getTime();//这样得到的差值是微秒级别
            long days = diff / (1000 * 60 * 60 * 24);

            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);

            Long day = new Long(days);
            Long hour = new Long(hours);
            Long minute = new Long(minutes);
            Long min_zero = new Long(0);
            Long min_one = new Long(1);
            logger.info(""+days+"天"+hours+"小时"+minutes+"分钟");
            logger.info(" "+(hours<1));
            int day1 = day.intValue();
            int hour1 = hour.intValue();
            int minute1 = minute.intValue();
            if( days > 0 || hours > 0 ){
                logger.info("执行力没啊");
                ModelAndView modelAndView;
                modelAndView = new ModelAndView("redirect:/linkInvalid");
                return modelAndView;
            }
        }catch (Exception e){
            logger.info(e.getMessage());
        }



        HttpSession session = request.getSession();
        session.setAttribute("resetpswemail", email);
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("redirect:/findpswResetPsw");
        return modelAndView;
    }

    @PostMapping("/resetpassword")
    public ResultVO<Integer> resetPassword(@RequestParam String useremail, @RequestParam String newpsw , HttpServletRequest request){
        User user = userService.findByEmail(useremail);
        if( user == null ){
            ResultVO<Integer> resultVO = new ResultVO<>();
            resultVO.setCode(ResultCode.NOT_FOUND.getCode());
            resultVO.setMessage("更新用户不存在");
            return resultVO;
        }else {
            int userResult = userService.updateUserPswByEmail(useremail,newpsw);
            ResultVO<Integer> resultVO = new ResultVO<>(userResult);

            HttpSession session = request.getSession();
            ShowUser showUser = new ShowUser();
            showUser.setId(user.getId());
            showUser.setEmail(user.getEmail());
            showUser.setUsername(user.getUsername());
            session.setAttribute("user",showUser);

            String loginfoCode = String.valueOf(resultVO.getCode());
            logger.info(loginfoCode);
            return resultVO;
        }
    }
}
