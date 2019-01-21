package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ShowUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * created by Kimone
 * date 2019/1/2
 */
@Controller
public class ViewController {

    @GetMapping("")
    public String test(){
        return "main";
    }

    @RequestMapping("/storyMapping")
    public ModelAndView test(@RequestParam int proId){
        ModelAndView modelAndView = new ModelAndView("storymap");
        modelAndView.addObject("proId",proId);
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request){
        HttpSession session = request.getSession();
        ShowUser showUser = (ShowUser) session.getAttribute("user");
        ModelAndView modelAndView ;
        if( showUser == null){
            modelAndView = new ModelAndView("login");
        }else {
            modelAndView = new ModelAndView("redirect:/project");
        }
        return modelAndView;
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
//        return "register";
    }

    //测试用的（测试跳转到main 主页）
    @RequestMapping("/main")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("main");
        return modelAndView;
    }

}
