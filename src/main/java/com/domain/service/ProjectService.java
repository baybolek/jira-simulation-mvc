package com.domain.service;

import com.domain.dto.ProjectDTO;
import com.domain.dto.UserDTO;

import java.util.List;


public interface ProjectService extends CrudService<ProjectDTO, String>{

    void complete(ProjectDTO project);

//    getCountedLiostOfProjectDTO

    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO user);

}
