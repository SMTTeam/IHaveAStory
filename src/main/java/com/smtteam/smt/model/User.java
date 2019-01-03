package com.smtteam.smt.model;

import com.smtteam.smt.common.bean.ShowUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String psw;

    private String username;

    private Integer gender;

    private String verify;



    public ShowUser toShowUser() {
        ShowUser user = new ShowUser();
        user.setEmail(email);
        user.setId(id);
        user.setUsername(username);
        return user;
    }

    public User (String email, String psw, String username, Integer gender, String verify){
        this.email = email;
        this.psw = psw;
        this.username = username;
        this.gender = gender;
        this.verify = verify;
    }
}