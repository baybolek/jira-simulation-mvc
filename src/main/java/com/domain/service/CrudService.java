package com.domain.service;

import com.domain.dto.RoleDTO;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface CrudService<T, ID> {
    T save(T object);
    T findById(ID id);
    List<T> findAll();
    void delete(T role);
    void deleteById(ID id);
}
