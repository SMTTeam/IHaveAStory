package com.smtteam.smt.common.bean;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 类说明：展示的用户类
 * 创建者：Zeros
 * 创建时间：2019-01-02 13:13
 * 包名：com.smtteam.smt.common.bean
 */

@Setter
@Getter
public class ShowUser implements Serializable {
    private Integer id;

    private String email;

    private String username;

}
