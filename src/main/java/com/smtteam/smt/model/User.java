package com.smtteam.smt.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private Integer id;

    private String email;

    private String psw;

    private String username;

    private Integer gender;

    private String verify;

}