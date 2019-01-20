package com.smtteam.smt.common.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoryVO {
    private Integer taskId;

    private String name;

    private Integer storyPoint;

    private String priority;

    private String description;

    private Integer posId;

    private String acceptance;

    private Integer releaseId;

    public StoryVO(Integer taskId, String name, Integer storyPoint, String priority, String description, Integer posId, String acceptance, Integer releaseId) {
        this.taskId = taskId;
        this.name = name;
        this.storyPoint = storyPoint;
        this.priority = priority;
        this.description = description;
        this.posId = posId;
        this.acceptance = acceptance;
        this.releaseId = releaseId;
    }

    @Override
    public String toString() {
        return "StoryVO{" +
                "taskId=" + taskId +
                ", name='" + name + '\'' +
                ", storyPoint=" + storyPoint +
                ", priority=" + priority + '\'' +
                ", description='" + description + '\'' +
                ", posId=" + posId +
                ", acceptance='" + acceptance + '\'' +
                ", releaseId=" + releaseId +
                '}';
    }
}
