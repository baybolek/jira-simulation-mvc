package com.domain.controller;


import com.domain.dto.ProjectDTO;
import com.domain.dto.TaskDTO;
import com.domain.enums.Status;
import com.domain.service.ProjectService;
import com.domain.service.TaskService;
import com.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    TaskService taskService;
    ProjectService projectService;
    UserService userService;


    public TaskController(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createTask(Model model){

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees",userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());
        return "/task/create";
    }

    @PostMapping("/create")
    public String insertTask(@ModelAttribute("task") TaskDTO task, Model model){
        taskService.save(task);
        return "redirect:/task/create";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteUser(@PathVariable("taskId") Long taskId){

        taskService.deleteById(taskId);

        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId,Model model){

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees",userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/update";
    }

//    @PostMapping("/update/{taskId}")
//    public String updateTask(@PathVariable("taskId") Long taskId, TaskDTO task){
//        task.setId(taskId);
//        taskService.update(task);
//
//        return "redirect:/task/create";
//    }

    @PostMapping("/update/{id}")
    public String updateTask(TaskDTO task){
        taskService.update(task);
        return "redirect:/task/create";
    }

    @GetMapping("/archive")
    public String archiveTask(Model model){

        model.addAttribute("tasks",taskService.findArchivedTasks());
        return "/task/archive";
    }

    @GetMapping("/pending")
    public String pendingTask(Model model){

        model.addAttribute("tasks",taskService.findPendingTasks());
        return "/task/pending-tasks";
    }


    @GetMapping("/status-update/{taskId}")
    public String updateTaskStatus(@PathVariable("taskId") Long taskId, Model model){

        List<Status> statuses= Arrays.asList(Status.OPEN,Status.IN_PROGRESS,Status.COMPLETE);
            model.addAttribute("task", taskService.findById(taskId));

        model.addAttribute("tasks",taskService.findAll());
        model.addAttribute("statuses", statuses);
        return "/task/status-update";
    }

    @PostMapping("/status-update/{id}")
    public String updateTaskStatus(TaskDTO task){
        taskService.taskStatusUpdate(task);
        return "redirect:/task/pending";
    }

}
