package com.domain.service.impl;

import com.domain.dto.UserDTO;
import com.domain.service.UserService;

import java.util.List;

public class UserServiceImpl extends AbstractMapService<UserDTO, String> implements UserService {

    @Override
    public UserDTO save(UserDTO object) {
        return super.save(object.getUserName(), object);
    }

    @Override
    public UserDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }
}
