package com.domain.service;

import com.domain.dto.ProjectDTO;
import com.domain.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO, String>{

    List<UserDTO> getAllManagers();

}
