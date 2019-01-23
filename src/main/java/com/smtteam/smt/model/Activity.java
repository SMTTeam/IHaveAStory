package com.smtteam.smt.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pro_id")
    private Integer proId;

    private String name;

    @Column(name = "pos_id")
    private Integer posId;

    public Activity(Integer proId, String name, Integer posId){
        this.proId = proId;
        this.name = name;
        this.posId = posId;
    }

}