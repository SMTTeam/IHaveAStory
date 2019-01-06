package com.smtteam.smt.common.bean;

import lombok.Data;

/**
 * 类说明：展示的用户类
 * 创建者：Weishixin
 * 创建时间：2019-01-04
 * 包名：com.smtteam.smt.common.bean
 */

@Data
public class LoginVO {

    private String loginEmail;//登录时输入的邮箱

    private String loginPassword;//登录输入的密码
}
