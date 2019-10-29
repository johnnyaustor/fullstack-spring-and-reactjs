package com.jap.learn.ppmtool.services;

import com.jap.learn.ppmtool.domain.Backlog;
import com.jap.learn.ppmtool.domain.Project;
import com.jap.learn.ppmtool.domain.User;
import com.jap.learn.ppmtool.exceptions.ProjectIdException;
import com.jap.learn.ppmtool.exceptions.ProjectNotFoundException;
import com.jap.learn.ppmtool.repositories.BacklogRepository;
import com.jap.learn.ppmtool.repositories.ProjectRepository;
import com.jap.learn.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdateProject(Project project, String username) {

        try {
            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());

            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);
        } catch (Exception ex) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }

    }

    public Project findProjectByIdentifier(String projectId, String username) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId.toUpperCase() + "' does not exist");
        }

        if (!project.getProjectLeader().equals(username)) {
            throw new ProjectNotFoundException("Project Not Found in your account");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(String username) {
        return projectRepository.findAllByProjectLeader(username);
    }

    public void deleteProjectByIdentifier(String projectId, String username) {
        projectRepository.delete(findProjectByIdentifier(projectId, username));
    }
}
