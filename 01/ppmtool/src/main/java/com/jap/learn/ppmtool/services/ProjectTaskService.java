package com.jap.learn.ppmtool.services;

import com.jap.learn.ppmtool.domain.Backlog;
import com.jap.learn.ppmtool.domain.Project;
import com.jap.learn.ppmtool.domain.ProjectTask;
import com.jap.learn.ppmtool.exceptions.ProjectNotFoundException;
import com.jap.learn.ppmtool.repositories.BacklogRepository;
import com.jap.learn.ppmtool.repositories.ProjectRepository;
import com.jap.learn.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        // Exception : project not found

        try {
            // PTS to be added to a specific project, project != null, BL exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            // set the backlog to projecttask
            projectTask.setBacklog(backlog);
            // we want our project sequence to be like : idpro-1 idpro-2 ... 100
            Integer backlogSequence = backlog.getPTSequence();
            // update the backlog sequence
            backlogSequence++;

            backlog.setPTSequence(backlogSequence);
            // add sequence to project task
            projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            // initial priority when priority null
            if (projectTask.getPriority() == null || projectTask.getPriority() == 0 ) {
                projectTask.setPriority(3);
            }
            // initial status when status null
            if (projectTask.getStatus() == null || projectTask.getStatus().equals("")) {
                projectTask.setStatus("TO_DO");
            }

            return projectTaskRepository.save(projectTask);
        } catch (Exception ex) {
            throw new ProjectNotFoundException("Project not found");
        }


    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id) {
        Project project = projectRepository.findByProjectIdentifier(backlog_id);

        if (project == null) {
            throw new ProjectNotFoundException("Project with ID: '" + backlog_id + "' does not exist");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }
}
