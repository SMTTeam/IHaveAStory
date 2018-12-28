package com.smtteam.smt.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "project")
public class Project {
    @Id
    private Integer id;

    private String proName;

    private String description;
}