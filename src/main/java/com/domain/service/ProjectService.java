package com.domain.service;

import com.domain.dto.ProjectDTO;


public interface ProjectService extends CrudService<ProjectDTO, String>{

    void complete(ProjectDTO project);


}
