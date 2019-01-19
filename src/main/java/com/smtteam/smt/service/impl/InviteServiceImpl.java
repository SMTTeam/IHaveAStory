package com.smtteam.smt.service.impl;

import com.alibaba.fastjson.JSON;
import com.smtteam.smt.common.bean.Constants;
import com.smtteam.smt.common.enums.ProjectRole;
import com.smtteam.smt.common.exception.ExistException;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.dao.ProjectUserDao;
import com.smtteam.smt.model.ProjectUser;
import com.smtteam.smt.service.InviteService;
import com.smtteam.smt.util.EncryptUtil;
import com.smtteam.smt.util.EnumUtil;
import com.smtteam.smt.util.MailUtil;
import com.smtteam.smt.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.NoSuchElementException;

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
        url = server + "/api/invite/accept/" + Base64.getEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
        String content = "<html><head><title></title></head><body>亲爱的SMT用户您好,<br> &nbsp;&nbsp;&nbsp;其他用户邀请您加入" + proName + "项目，点击以下链接查看详情：<br> &nbsp;&nbsp; <a href = \"" + url + "\">" + url + "</a></body></html>";
        System.out.println(content);
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
}
