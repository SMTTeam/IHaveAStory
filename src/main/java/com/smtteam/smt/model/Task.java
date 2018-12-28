package com.smtteam.smt.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    private Integer id;

    private Integer activityId;

    private String name;

    private Integer posId;
}