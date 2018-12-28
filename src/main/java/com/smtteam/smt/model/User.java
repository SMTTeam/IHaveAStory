package com.smtteam.smt.model;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String email;

    private String psw;

    private String username;

    private Integer gender;

    private String verify;

}