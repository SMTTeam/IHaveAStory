package com.smtteam.smt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String proName;

    private String description;


    public Project(Integer userId, String proName, String description) {
        this.userId = userId;
        this.proName = proName;
        this.description = description;
    }
}