package com.jap.learn.ppmtool.services;

import com.jap.learn.ppmtool.domain.Project;
import com.jap.learn.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        return this.projectRepository.save(project);
    }
}
