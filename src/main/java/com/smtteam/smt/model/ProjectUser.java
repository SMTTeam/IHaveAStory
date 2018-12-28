package com.smtteam.smt.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "pro_user")
public class ProjectUser {
    @Id
    private Integer id;

    private Integer proId;

    private Integer userId;

    private Integer role;

    private Integer status;

    private String salt;


}