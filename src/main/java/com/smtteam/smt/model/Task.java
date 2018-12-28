package com.smtteam.smt.model;

import lombok.Data;

@Data
public class Task {
    private Integer id;

    private Integer activityId;

    private String name;

    private Integer posId;
}