package com.smtteam.smt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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


}