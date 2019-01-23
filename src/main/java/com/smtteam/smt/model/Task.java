package com.smtteam.smt.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer activityId;

    private String name;

    private Integer posId;

    public Task(Integer activityId, String name, Integer posId) {
        this.activityId = activityId;
        this.name = name;
        this.posId = posId;
    }
}