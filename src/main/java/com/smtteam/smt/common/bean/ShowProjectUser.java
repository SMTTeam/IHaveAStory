package com.smtteam.smt.common.bean;

import lombok.Data;

/**
 * 类说明：展示的项目用户类
 * 创建者：Zeros
 * 创建时间：2019-01-02 13:13
 * 包名：com.smtteam.smt.common.bean
 */

@Data
public class ShowProjectUser {
    private Integer proId;

    private Integer userId;

    private String avatar;

    private String username;

    private Integer role;

    //1-邀请中，2-已加入
    private Integer status;
}
