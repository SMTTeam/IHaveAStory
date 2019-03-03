package com.smtteam.smt.common.bean;

/**
 * 类说明：常量类
 * 创建者：Zeros
 * 创建时间：2018-12-31 16:15
 * 包名：com.smtteam.smt.common.bean
 */

public class Constants {

    public final static Integer PROJECT_INVITING = 1;
    public final static Integer PROJECT_INVITED = 2;

    //邮箱验证常量
    public final static Integer USEREMAIL_VERIFYING = 1;
    public final static Integer USEREMAIL_VERIFIED = 2;

    //默认的用户性别
    public final static Integer USER_DEFAULT_GENDA = 1;

    //默认verify字段为""
    public final static String USER_DEFAULT_VERIFY = "";

    //盐长度
    public static final Integer SALT_LENGTH = 20;

    //找回密码最大有效时间
    public static final Integer MAX_VALIDT_IME_TO_FIND_BACK_PASSWORD = 8 ;
}
