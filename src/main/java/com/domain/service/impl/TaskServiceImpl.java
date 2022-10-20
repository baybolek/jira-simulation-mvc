package com.domain.service.impl;

import com.domain.dto.TaskDTO;
import com.domain.dto.UserDTO;
import com.domain.enums.Status;
import com.domain.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO, Long> implements TaskService {

    @Override
    public TaskDTO save(TaskDTO object) {
        if(object.getTaskStatus()==null){
            object.setTaskStatus(Status.OPEN);
        }

        if (object.getAssignedDate() == null) {
            object.setAssignedDate(LocalDate.now());
        }

        if (object.getId() == null) {
            object.setId(UUID.randomUUID().getMostSignificantBits());
        }

        return super.save(object.getId(), object);
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(TaskDTO object) {
        TaskDTO foundTask = findById(object.getId());

        object.setTaskStatus(foundTask.getTaskStatus());
        object.setAssignedDate(foundTask.getAssignedDate());

        super.update(object.getId(), object);
    }


    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public List<TaskDTO> findPendingTasks() {
        return super.findAll().stream().filter(task->task.getTaskStatus().getValue().equals("In Progress")).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findArchivedTasks() {
        return super.findAll().stream().filter(task->task.getTaskStatus().getValue().equals("Completed")).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findTasksByManager(UserDTO manager) {
        return findAll().stream().filter(task->task.getProject().getAssignedManager().equals(manager)).collect(Collectors.toList());
    }

    @Override
    public void taskStatusUpdate(TaskDTO task) {
        findById(task.getId()).setTaskStatus(task.getTaskStatus());
        update(task);
    }
}
