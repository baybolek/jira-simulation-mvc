package com.domain.service.impl;

import com.domain.dto.ProjectDTO;
import com.domain.dto.UserDTO;
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
        super.update(object.getProjectCode(), object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

}
