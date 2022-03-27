package com.community.client.services;

import com.community.client.models.Project;
import com.community.client.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

    //Dependency Injection of the project repository
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    //Methods in the service
    public Project saveProject(Project project){
        return projectRepository.save(project);
    }

    public Set<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id){
        Optional<Project> projectOptional = projectRepository.findProjectById(id);
        if (projectOptional.isPresent()){
            return projectOptional.get();
        }else {
            return null;
        }
    }

    public Set<Project> getProjectByName(String keyword){
        return projectRepository.findProjectByName(keyword);
    }

}
