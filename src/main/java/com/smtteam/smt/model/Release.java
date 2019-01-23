package com.smtteam.smt.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "iteration")
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer proId;

    private String name;

    private Integer posId;

    public Release(Integer proId, String name, Integer posId) {
        this.proId = proId;
        this.name = name;
        this.posId = posId;
    }
}