package com.smtteam.smt.model;

import lombok.Data;

@Data
public class ProjectUser {
    private Integer puId;

    private Integer proId;

    private Integer userId;

    private Integer role;

    private Integer status;

    private String salt;


}