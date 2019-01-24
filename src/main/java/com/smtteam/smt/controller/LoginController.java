package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.Constants;
import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.common.enums.ResultCode;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * createdBy: weishixin
 * date: 2018/01/04
 */
@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 检查用户是否是已注册系统用户
     * @param email
     * @param pwd
     * @return
     */
    @PostMapping("/checklogin")
    public ResultVO<User> checkLogin(@RequestParam String email , @RequestParam String pwd , HttpServletRequest request){
        User tempResult = userService.findByEmailAndStatus(email , Constants.USEREMAIL_VERIFIED);

        if( tempResult == null ){//首先验证邮箱是否已经验证
            ResultVO<User> resultVO = new ResultVO<>();
            resultVO.setCode(ResultCode.NOT_FOUND.getCode());
            resultVO.setMessage("邮箱未验证");
            logger.info("邮箱未验证");
            return resultVO;
        }else {
            User result = userService.findByEmailAndPsw(email , pwd);
            if( result == null ){
                ResultVO<User> resultVO = new ResultVO<>();
                resultVO.setCode(ResultCode.NOT_FOUND.getCode());
                resultVO.setMessage("用户名或密码错误");
                String infoCode = String.valueOf(resultVO.getCode());
                logger.info(infoCode);
                return resultVO;
            }else {
                ResultVO<User> resultVO = new ResultVO<>(result);
                String infoCode = String.valueOf(resultVO.getCode());
                logger.info(infoCode);
                HttpSession session = request.getSession();
                ShowUser showUser = new ShowUser();//使用showUser屏蔽用户密码
                showUser.setId(result.getId());
                showUser.setEmail(result.getEmail());
                showUser.setUsername(result.getUsername());
                session.setAttribute("user",showUser);
                return resultVO;
            }
        }
    }

    /**
     * 获取用户登录状态
     * @param request
     * @return
     */
    @GetMapping("/getuserloginstate")
    public ResultVO<ShowUser> getUserLoginState( HttpServletRequest request){
        HttpSession session = request.getSession();

        ShowUser showUser = (ShowUser) session.getAttribute("user");

        ResultVO<ShowUser> resultVO = new ResultVO<>(showUser);
        logger.info("查询成功，用户已登录！");
        return resultVO;
    }

    @GetMapping("/exit")
    public ModelAndView exitLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("user",null);
        return new ModelAndView("redirect:/login2");
    }
}
