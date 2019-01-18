package com.smtteam.smt.common.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class IterationVO {
    private Integer iteration;

    private String groupName;

    public IterationVO(Integer iteration, String groupName) {
        this.iteration = iteration;
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        IterationVO that = (IterationVO) o;
        return Objects.equals(iteration, that.iteration) &&
                Objects.equals(groupName, that.groupName);
    }

}
