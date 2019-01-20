package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.model.ProjectUser;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.InviteService;
import com.smtteam.smt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 类说明：项目界面路由
 * 创建者：Zeros
 * 创建时间：2019-01-11 00:08
 * 包名：com.smtteam.smt.controller
 */

@Controller
public class ProjectController {
    @Autowired
    private InviteService inviteService;

    @Autowired
    private UserService userService;

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

    @RequestMapping("project/edit")
    public String editProject(){
        return "editProject";
    }

    @GetMapping("invite/accept/{code}")
    public String acceptInvitation(@PathVariable String code, HttpServletRequest request, HttpServletResponse response){
        if(code == null || code.isEmpty()){
            return "error";
        }
        ProjectUser projectUser = null;
        try {
            projectUser = inviteService.acceptInvitation(code);
            User user = userService.findById(projectUser.getUserId());
            HttpSession session = request.getSession();
            ShowUser showUser = user.toShowUser();
            session.setAttribute("user",showUser);
            response.sendRedirect("/storyMapping?proId=" + projectUser.getProId());
        } catch (NoAccessException | NumberFormatException e) {
            return "error";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
