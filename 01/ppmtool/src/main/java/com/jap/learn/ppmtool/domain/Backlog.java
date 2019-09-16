package com.jap.learn.ppmtool.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence = 0;
    private String projectIdentifier;

    // OneToOne with project

    // OneToMany with projectTasks


    public Backlog() {
    }

    public Long getId() {
        return id;
    }
    public Integer getPTSequence() {
        return PTSequence;
    }
    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPTSequence(Integer PTSquence) {
        this.PTSequence = PTSquence;
    }
    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
