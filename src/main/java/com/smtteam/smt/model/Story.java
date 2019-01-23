package com.smtteam.smt.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
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

    private Integer releaseId;

    public Story(Integer taskId, String name, Integer storyPoint, Integer priority, String description, Integer posId, String acceptance, Integer releaseId) {
        this.taskId = taskId;
        this.name = name;
        this.storyPoint = storyPoint;
        this.priority = priority;
        this.description = description;
        this.posId = posId;
        this.acceptance = acceptance;
        this.releaseId = releaseId;
    }
}