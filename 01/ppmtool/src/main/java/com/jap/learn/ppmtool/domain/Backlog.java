package com.jap.learn.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence = 0;
    private String projectIdentifier;

    // OneToOne with project
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;

    // OneToMany with projectTasks
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "backlog")
    private List<ProjectTask> projectTaskList = new ArrayList<>();


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
    public Project getProject() {
        return project;
    }
    public List<ProjectTask> getProjectTaskList() {
        return projectTaskList;
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
    public void setProject(Project project) {
        this.project = project;
    }
    public void setProjectTaskList(List<ProjectTask> projectTaskList) {
        this.projectTaskList = projectTaskList;
    }
}
