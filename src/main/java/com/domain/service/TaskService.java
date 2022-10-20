package com.domain.service;

import com.domain.dto.TaskDTO;
import com.domain.dto.UserDTO;
import com.domain.enums.Status;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO, Long>{

    List<TaskDTO> findPendingTasks();

    List<TaskDTO> findArchivedTasks();

    List<TaskDTO> findTasksByManager(UserDTO manager);

    void taskStatusUpdate(TaskDTO task);



}
