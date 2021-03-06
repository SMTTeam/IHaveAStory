package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ShowUser;
import com.sun.org.apache.xpath.internal.operations.Mod;
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

    @GetMapping("/storyMapping")
    public ModelAndView test(@RequestParam int proId){
        ModelAndView modelAndView = new ModelAndView("storymap");
        modelAndView.addObject("proId",proId);
        return modelAndView;
    }

    @GetMapping("/login")
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

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }


    @GetMapping("/findbackpsw")
    public ModelAndView findBackPsw(){
        return new ModelAndView("findpswSendEmail");
    }

    //test
    @GetMapping("/findpswResetPsw")
    public ModelAndView findPswResetPsw(){
        return new ModelAndView("findpswResetPsw");
    }

    @GetMapping("/linkInvalid")
    public ModelAndView linkInvalid(){
        return new ModelAndView("LinkIsInvalid");
    }
}
