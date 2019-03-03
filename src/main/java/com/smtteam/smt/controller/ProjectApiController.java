package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.common.enums.ResultCode;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.model.Project;
import com.smtteam.smt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 类说明：
 * 创建者：Zeros
 * 创建时间：2018-12-29 16:08
 * 包名：com.smtteam.smt.controller
 */
@RestController
@RequestMapping("api/project")
public class ProjectApiController {

    @Autowired
    private ProjectService projectService;

    /**
     * 创建项目
     * @param proName
     * @param description
     * @return
     */
    @PostMapping("create")
    public ResultVO<Project> createProject(@RequestParam String proName, @RequestParam String description, HttpServletRequest request){
        HttpSession session = request.getSession();
        ShowUser showUser = (ShowUser) session.getAttribute("user");
        Integer userId = showUser.getId();

        Project project = new Project(userId, proName, description);
        Project result = projectService.createProject(project);
        return new ResultVO<>(result);
    }

    /**
     * 修改项目
     * @param proId
     * @param proName
     * @param description
     * @param request
     * @return
     */
    @PostMapping("modify")
    public ResultVO<Project> modifyProject(@RequestParam Integer proId, @RequestParam String proName, @RequestParam String description, HttpServletRequest request){
        HttpSession session = request.getSession();
        ShowUser showUser = (ShowUser) session.getAttribute("user");
        Integer userId = showUser.getId();

        Project project = null;
        try {
            project = projectService.modifyProject(proId, userId, proName, description);
        } catch (NoAccessException e) {
            return new ResultVO<>(ResultCode.NOT_ACCESS, "没有权限访问这个项目。");
        }
        return new ResultVO<>(project);
    }


    /**
     * 查看我发布的项目
     * @return
     */
    @GetMapping("list")
    public ResultVO<List<Project>> getReleaseList(HttpServletRequest request){
        HttpSession session = request.getSession();
        ShowUser showUser = (ShowUser) session.getAttribute("user");
        Integer userId = showUser.getId();

        List<Project> projectList = projectService.findReleaseList(userId);
        return new ResultVO<>(projectList);
    }

    /**
     * 查看我参与的项目
     * @return
     */
    @GetMapping("attended")
    public ResultVO<List<Project>> getAttendedList(HttpServletRequest request){
        HttpSession session = request.getSession();
        ShowUser showUser = (ShowUser) session.getAttribute("user");
        Integer userId = showUser.getId();

        List<Project> projectList = projectService.findAttendList(userId);
        return new ResultVO<>(projectList);
    }

    /**
     * 获取项目详情
     * @param proId
     * @param request
     * @return
     */
    @GetMapping("detail")
    public ResultVO<Project> getProjectDetail(@RequestParam Integer proId, HttpServletRequest request){
        Project project  = projectService.findById(proId);
        return new ResultVO<>(project);
    }

    /**
     * 删除项目
     */
    @PostMapping("delete")
    public ResultVO<Void> deleteProject(@RequestParam Integer proId, HttpServletRequest request){
        HttpSession session = request.getSession();
        ShowUser showUser = (ShowUser) session.getAttribute("user");
        Integer userId = showUser.getId();
        boolean result = projectService.deleteProject(proId, userId);
        return result ? new ResultVO<>() : new ResultVO<>("项目不存在或您没有该项目的权限。");
    }


}
