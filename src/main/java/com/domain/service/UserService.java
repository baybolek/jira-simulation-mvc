package com.domain.service;

import com.domain.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService<UserDTO, String>{


    List<UserDTO> findManagers();
}
