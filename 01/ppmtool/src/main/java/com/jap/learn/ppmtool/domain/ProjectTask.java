package com.jap.learn.ppmtool.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private String projectSequence;
    @NotBlank(message = "Please include a project summary")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    private Date dueDate;
    // ManyToOne with backlog

    @Column(updatable = false)
    private String projectIdentifier;
    private Date created_At;
    private Date updated_At;

    public ProjectTask() {
    }

    public Long getId() {
        return id;
    }
    public String getProjectSequence() {
        return projectSequence;
    }
    public String getSummary() {
        return summary;
    }
    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }
    public String getStatus() {
        return status;
    }
    public Integer getPriority() {
        return priority;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public String getProjectIdentifier() {
        return projectIdentifier;
    }
    public Date getCreated_At() {
        return created_At;
    }
    public Date getUpdated_At() {
        return updated_At;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }
    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }

    @Override
    public String toString() {
        return "ProjectTask{" +
                "id=" + id +
                ", projectSequence='" + projectSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", created_At=" + created_At +
                ", updated_At=" + updated_At +
                '}';
    }
}
