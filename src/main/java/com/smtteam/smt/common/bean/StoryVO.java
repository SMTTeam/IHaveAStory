package com.smtteam.smt.common.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoryVO {
    private Integer taskId;

    private String name;

    private Integer storyPoint;

    private Integer priority;

    private String description;

    private Integer posId;

    private String acceptance;

    private Integer iteration;

    public StoryVO(Integer taskId, String name, Integer storyPoint, Integer priority, String description, Integer posId, String acceptance, Integer iteration) {
        this.taskId = taskId;
        this.name = name;
        this.storyPoint = storyPoint;
        this.priority = priority;
        this.description = description;
        this.posId = posId;
        this.acceptance = acceptance;
        this.iteration = iteration;
    }

    @Override
    public String toString() {
        return "StoryVO{" +
                "taskId=" + taskId +
                ", name='" + name + '\'' +
                ", storyPoint=" + storyPoint +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                ", posId=" + posId +
                ", acceptance='" + acceptance + '\'' +
                ", iteration=" + iteration +
                '}';
    }
}