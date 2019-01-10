package com.smtteam.smt.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * created by Kimone
 * date 2019/1/2
 */
@Controller
public class ViewController {

    @RequestMapping("/storyMapping")
    public ModelAndView test(@RequestParam int proId){
        ModelAndView modelAndView = new ModelAndView("storymap");
        modelAndView.addObject("proId",proId);
        return modelAndView;
    }

    @RequestMapping("/loginsmt")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
//        return "login";
    }

    @RequestMapping("/registersmt")
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


    @RequestMapping("project")
    public String project(){
        return "projects";
    }

    @RequestMapping("attended")
    public String attended(){
        return "attended";
    }

}
