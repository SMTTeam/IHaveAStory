package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.model.Project;
import com.smtteam.smt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @param title
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
}
