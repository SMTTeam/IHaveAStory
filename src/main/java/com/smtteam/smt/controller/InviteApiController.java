package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.bean.ShowProjectUser;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.common.enums.ProjectRole;
import com.smtteam.smt.common.exception.ExistException;
import com.smtteam.smt.model.Project;
import com.smtteam.smt.model.ProjectUser;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.InviteService;
import com.smtteam.smt.service.ProjectService;
import com.smtteam.smt.service.UserService;
import com.smtteam.smt.util.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * 类说明：邀请Controller
 * 创建者：Zeros
 * 创建时间：2019-01-01 18:27
 * 包名：com.smtteam.smt.controller
 */

@RestController
@RequestMapping("api/invite")
public class InviteApiController {

    @Autowired
    private InviteService inviteService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;


    /**
     * 搜索邮箱
     * @param email
     * @return
     */
    @GetMapping("search")
    public ResultVO<List<ShowUser>> search(@RequestParam String email){
        List<User> users = userService.findByEmailLike(email);
        List<ShowUser> userList = users.stream().map(User::toShowUser).collect(Collectors.toList());
        return new ResultVO<>(userList);
    }

    /**
     * 发出邀请
     * @param email
     * @param proId
     * @param role 权限，对应枚举类
     * @return
     */
    @PostMapping("create")
    public ResultVO<Void> sendInviteEmail(@RequestParam String email, @RequestParam Integer proId, @RequestParam Integer role, HttpServletRequest request){
        User user = userService.findByEmailAndStatus(email);
        if(user == null){
            return new ResultVO<>("用户未注册或未通过验证。");
        }
        //获取操作者用户ID
        HttpSession session = request.getSession();
        ShowUser showUser = (ShowUser) session.getAttribute("user");
        Integer ownerId = showUser.getId();
        //存在判断
        ProjectUser projectUser = inviteService.findProjectUser(proId, ownerId);
        Project project = projectService.findById(proId);
        if(project == null || projectUser == null) {
            return new ResultVO<>("项目不存在或者您未参与该项目。");
        }

        try {
            //权限判断
            ProjectRole projectRole = EnumUtil.getEnumByField(ProjectRole.class, "role", projectUser.getRole());
            if(projectRole == null || !projectRole.canEditProject()){
                return new ResultVO<>("项目不存在或者您没有权限管理这个项目。");
            }
            inviteService.createInvitation(user.getId(), user.getEmail(), proId, project.getProName(), role);
        } catch (NoSuchElementException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return new ResultVO<>("权限输入有误。");
        } catch (ExistException e) {
            return new ResultVO<>("该用户已被邀请或者已在项目中。");
        }
        return new ResultVO<>();
    }


    /**
     * 获取用户对项目的权限
     */
    @GetMapping("role")
    public ResultVO<ProjectUser> getProjectRole(@RequestParam Integer proId, @RequestParam Integer userId, HttpServletRequest request){
        ProjectUser role  = inviteService.findProjectUser(proId, userId);
        return new ResultVO<>(role);
    }

    @GetMapping("list")
    public ResultVO<List<ShowProjectUser>> getInviteList(@RequestParam Integer proId, HttpServletRequest request){
        List<ShowProjectUser> userList  = inviteService.findInviteList(proId);
        return new ResultVO<>(userList);
    }

    @PostMapping("delete")
    public ResultVO<Void> deleteInvite(@RequestParam Integer proId, @RequestParam Integer userId, HttpServletRequest request){
        HttpSession session = request.getSession();
        ShowUser showUser = (ShowUser) session.getAttribute("user");
        Integer askUserId = showUser.getId();
        boolean result = inviteService.deleteInvite(proId, userId, askUserId);
        return result ? new ResultVO<>() : new ResultVO<>("对应项目或用户不存在，同时请确认您是否有编辑该项目的权限。");
    }

}
