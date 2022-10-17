package com.domain.service;

import com.domain.dto.TaskDTO;
import com.domain.dto.UserDTO;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO, Long>{

    List<TaskDTO> findPendingTasks();

    List<TaskDTO> findArchivedTasks();
}
