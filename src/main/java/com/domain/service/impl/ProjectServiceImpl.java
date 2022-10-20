package com.domain.service.impl;

import com.domain.dto.ProjectDTO;
import com.domain.dto.TaskDTO;
import com.domain.dto.UserDTO;
import com.domain.enums.Status;
import com.domain.service.ProjectService;
import com.domain.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {

    TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

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

        ProjectDTO newProject=findById(object.getProjectCode());
        if(object.getProjectStatus()==null){
            object.setProjectStatus(newProject.getProjectStatus());
        }
        super.update(object.getProjectCode(), object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }


    @Override
    public void complete(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETE);
        super.save(project.getProjectCode(), project);
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList=
                findAll()
                        .stream()
                        .filter(project->project.getAssignedManager().equals(manager))
                        .map(project->{

                            List<TaskDTO> taskList=taskService.findTasksByManager(manager);

                            int completeTaskCounts= (int) taskList.stream().filter(t->t.getProject().equals(project)&&t.getTaskStatus()==Status.COMPLETE).count();

                            int unfinishedTaskCounts=(int) taskList.stream().filter(t->t.getProject().equals(project)&&t.getTaskStatus()!=Status.COMPLETE).count();

                            project.setCompleteTaskCounts(completeTaskCounts);
                            project.setUnfinishedTaskCounts(unfinishedTaskCounts);
                            return project;
                        }).collect(Collectors.toList());


        return projectList;
    }


}
