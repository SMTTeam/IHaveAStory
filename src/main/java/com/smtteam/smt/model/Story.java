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

}