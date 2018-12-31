package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.model.Project;
import com.smtteam.smt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明：
 * 创建者：Zeros
 * 创建时间：2018-12-29 16:08
 * 包名：com.smtteam.smt.controller
 */
@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 创建项目
     * @param name
     * @param description
     * @return
     */
    @PostMapping("create")
    public ResultVO<Project> createProject(@RequestParam String name, @RequestParam  String description){
        //TODO 获取用户ID
        Project project = new Project(1, name, description);
        Project result = projectService.createProject(project);
        return new ResultVO<>(result);
    }

    /**
     * 查看我发布的项目
     * @return
     */
    @GetMapping("list")
    public ResultVO<List<Project>> getReleaseList(){
        //TODO 获取用户ID
        List<Project> projectList = projectService.findByUserId(1);
        return new ResultVO<>(projectList);
    }
}
