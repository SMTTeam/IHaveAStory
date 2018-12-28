package com.smtteam.smt.model;

import lombok.Data;

@Data
public class Story {
    private Integer id;

    private Integer taskId;

    private String name;

    private Integer storyPoint;

    private Integer priority;

    private String description;

    private Integer posId;

    private String acceptance;

}