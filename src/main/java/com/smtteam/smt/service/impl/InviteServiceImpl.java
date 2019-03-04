package com.smtteam.smt.service.impl;

import com.smtteam.smt.common.bean.Constants;
import com.smtteam.smt.common.bean.ShowProjectUser;
import com.smtteam.smt.common.enums.ProjectRole;
import com.smtteam.smt.common.exception.ExistException;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.dao.ProjectUserDao;
import com.smtteam.smt.dao.UserDao;
import com.smtteam.smt.model.Project;
import com.smtteam.smt.model.ProjectUser;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.InviteService;
import com.smtteam.smt.util.EncryptUtil;
import com.smtteam.smt.util.EnumUtil;
import com.smtteam.smt.util.MailUtil;
import com.smtteam.smt.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 类说明：邀请具体实现
 * 创建者：Zeros
 * 创建时间：2019-01-01 20:28
 * 包名：com.smtteam.smt.service.impl
 */

@Service
public class InviteServiceImpl implements InviteService {
    @Autowired
    private ProjectUserDao projectUserDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MailUtil mailUtil;

    @Value("${deploy.url}")
    private String server;

    @Override
    public ProjectUser createInvitation(Integer userId, String email, Integer proId, String proName, Integer role) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ExistException {
        //是否已经在邀请
        ProjectUser temp = projectUserDao.findByUserIdAndProId(userId, proId);
        if(temp != null) throw new ExistException("该用户已被邀请或者已在项目中。");
        //权限转换
        ProjectRole projectRole = EnumUtil.getEnumByField(ProjectRole.class,"role", role);
        if(projectRole == null){
            throw new NoSuchElementException();
        }
        //保存记录
        String salt = StringUtil.getSalt();
        ProjectUser projectUser = new ProjectUser(proId, userId, projectRole.getRole(), Constants.PROJECT_INVITING, salt);
        projectUser = projectUserDao.save(projectUser);

        String url = userId + "&" + proId + "&" + EncryptUtil.SHA256(salt);
        url = server + "/invite/accept/" + Base64.getEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
        String content = "<html><head><title></title></head><body>亲爱的SMT用户您好,<br> &nbsp;&nbsp;&nbsp;其他用户邀请您加入" + proName + "项目，点击以下链接加入此项目：<br> &nbsp;&nbsp; <a href = \"" + url + "\">" + url + "</a></body></html>";
        String[] tos = new String[]{email};
        mailUtil.sendHtmlMail(tos, "项目邀请", content);
        return projectUser;
    }

    /**
     * 接受邀请
     * @param code base64编码的密钥
     * @return
     */
    @Override
    public ProjectUser acceptInvitation(String code) throws NoAccessException {
        byte[] bytes = Base64.getDecoder().decode(code);
        String source = new String(bytes, StandardCharsets.UTF_8);
        String[] array = source.split("&");
        if(array.length < 3) throw new NoAccessException("没有权限");
        Integer userId = Integer.parseInt(array[0]);
        Integer proId = Integer.parseInt(array[1]);
        //检验
        ProjectUser projectUser = projectUserDao.findByUserIdAndProId(userId, proId);
        if(projectUser == null || !projectUser.getStatus().equals(Constants.PROJECT_INVITING) || !array[2].equals(EncryptUtil.SHA256(projectUser.getSalt()))){
            throw new NoAccessException("邀请不存在或您已同意加入该项目。");
        }
        //更新状态
        projectUser.setStatus(Constants.PROJECT_INVITED);
        return projectUserDao.saveAndFlush(projectUser);
    }

    /**
     * 查看用户对项目的权限
     * @param proId
     * @param userId
     * @return
     */
    @Override
    public ProjectUser findProjectUser(Integer proId, Integer userId) {
        return projectUserDao.findByUserIdAndProId(userId, proId);
    }

    /**
     * 获取项目的参与列表
     *
     * @param proId
     * @return
     */
    @Override
    public List<ShowProjectUser> findInviteList(Integer proId) {
        List<ProjectUser> projectUsers = projectUserDao.findByProIdOrderByRoleDesc(proId);
        List<ShowProjectUser> showUsers = new ArrayList<>();
        for(ProjectUser projectUser : projectUsers){
            ShowProjectUser showInfo = new ShowProjectUser();
            BeanUtils.copyProperties(projectUser, showInfo);
            Optional<User> userInfo = userDao.findById(projectUser.getUserId());
            if(userInfo.isPresent()){
                User user = userInfo.get();
                //TODO showInfo.setAvatar(user.getAvatar());
                showInfo.setUsername(user.getUsername());
                showUsers.add(showInfo);
            }
        }
        return showUsers;
    }

    /**
     * 删除项目协作的成员
     * @return
     */
    @Override
    public boolean deleteInvite(Integer proId, Integer userId, Integer askUserId) {
        ProjectUser askUser = projectUserDao.findByUserIdAndProId(askUserId, proId);
        ProjectUser delUser = projectUserDao.findByUserIdAndProId(userId,proId);
        if(delUser == null || askUser == null || askUser.getRole() < ProjectRole.Project_Editor.getRole()){
            return false;
        }
        projectUserDao.deleteById(delUser.getId());
        return true;
    }


}
