package com.smtteam.smt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类说明：项目界面路由
 * 创建者：Zeros
 * 创建时间：2019-01-11 00:08
 * 包名：com.smtteam.smt.controller
 */

@Controller
public class ProjectController {

    @RequestMapping("project")
    public String project(){
        return "projects";
    }

    @RequestMapping("attended")
    public String attended(){
        return "attended";
    }

    @RequestMapping("project/create")
    public String createProject(){
        return "createProject";
    }
}
