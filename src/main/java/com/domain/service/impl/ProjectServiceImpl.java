package com.domain.service.impl;

import com.domain.dto.ProjectDTO;
import com.domain.enums.Status;
import com.domain.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {

    @Override
    public ProjectDTO save(ProjectDTO object) {
        if(object.getProjectStatus()==null){
            object.setProjectStatus(Status.OPEN);
        }

        return super.save(object.getProjectCode(),object);
    }

    @Override
    public ProjectDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {

        ProjectDTO newProject=findById(object.getProjectCode());
        if(object.getProjectStatus()==null){
            object.setProjectStatus(newProject.getProjectStatus());
        }
        super.update(object.getProjectCode(), object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }


    @Override
    public void complete(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETE);
        super.save(project.getProjectCode(), project);
    }
}
