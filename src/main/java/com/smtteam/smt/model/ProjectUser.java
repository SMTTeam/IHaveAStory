package com.smtteam.smt.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "pro_user")
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer proId;

    private Integer userId;

    private Integer role;

    //1-邀请中，2-已加入
    private Integer status;

    //加密
    private String salt;

    public ProjectUser(Integer proId, Integer userId, Integer role, Integer status, String salt) {
        this.proId = proId;
        this.userId = userId;
        this.role = role;
        this.status = status;
        this.salt = salt;
    }
}