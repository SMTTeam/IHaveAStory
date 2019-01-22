package com.smtteam.smt.common.bean;


/**
 * 类说明：展示的项目用户类
 * 创建者：Zeros
 * 创建时间：2019-01-02 13:13
 * 包名：com.smtteam.smt.common.bean
 */

public class ShowProjectUser {
    private Integer proId;

    private Integer userId;

    private String avatar;

    private String username;

    private Integer role;

    //1-邀请中，2-已加入
    private Integer status;

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
