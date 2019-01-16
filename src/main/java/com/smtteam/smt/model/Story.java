package com.smtteam.smt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer taskId;

    private String name;

    private Integer storyPoint;

    private Integer priority;

    private String description;

    private Integer posId;

    private String acceptance;

    private String groupName;

    private Integer iteration;

    public Story(Integer taskId, String name, Integer storyPoint, Integer priority, String description, Integer posId, String acceptance, String groupName, Integer iteration) {
        this.taskId = taskId;
        this.name = name;
        this.storyPoint = storyPoint;
        this.priority = priority;
        this.description = description;
        this.posId = posId;
        this.acceptance = acceptance;
        this.groupName = groupName;
        this.iteration = iteration;
    }
}