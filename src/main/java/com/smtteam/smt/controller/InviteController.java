package com.smtteam.smt.controller;

import com.smtteam.smt.common.bean.ResultVO;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.common.exception.ExistException;
import com.smtteam.smt.model.Project;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.InviteService;
import com.smtteam.smt.service.ProjectService;
import com.smtteam.smt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("invite")
public class InviteController {

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
    public ResultVO<List<ShowUser>> sendInviteEmail(@RequestParam String email){
        List<User> users = userService.findByEmailLike(email);
        List<ShowUser> userList = users.stream().map(User::toShowUser).collect(Collectors.toList());
        return new ResultVO<>(userList);
    }

    /**
     * 发出邀请
     * @param userId
     * @param proId
     * @param role 权限，对应枚举类
     * @return
     */
    @PostMapping("create")
    public ResultVO<Void> sendInviteEmail(@RequestParam Integer userId, @RequestParam Integer proId, @RequestParam Integer role){
        User user = userService.findById(userId);
        if(user == null){
            return new ResultVO<>("用户不存在。");
        }
        //TODO 获取用户ID
        Integer ownerId = 1;
        Project project = projectService.findById(proId);
        if(project == null || !project.getUserId().equals(ownerId)) {
            return new ResultVO<>("项目不存在或者您没有权限管理这个项目。");
        }
        try {
            inviteService.createInvitation(userId, user.getEmail(), proId, project.getProName(), role);
        } catch (NoSuchElementException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return new ResultVO<>("权限输入有误。");
        } catch (ExistException e) {
            e.printStackTrace();
            return new ResultVO<>("该用户已被邀请或者已在项目中。");
        }
        return new ResultVO<>();
    }

}
