package com.domain.controller;


import com.domain.dto.ProjectDTO;
import com.domain.dto.UserDTO;
import com.domain.service.ProjectService;
import com.domain.service.RoleService;
import com.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    RoleService roleService;
    UserService userService;
    ProjectService projectService;

    public ProjectController(RoleService roleService, UserService userService, ProjectService projectService) {
        this.roleService = roleService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers", userService.findManagers());

        return "/project/create";
    }


    @PostMapping("/create")
    public String insertProject(ProjectDTO project, Model model){
        projectService.save(project);
        return "redirect:/project/create";
    }


    @GetMapping("/update/{projectCode}")
    public String editUser(@PathVariable("projectCode") String projectCode,Model model){

        model.addAttribute("project", projectService.findById(projectCode));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers", userService.findManagers());


        return "/project/update";
    }

    @PostMapping("/update")
    public String updateProject(ProjectDTO project){

        projectService.update(project);
        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectCode}")
    public String deleteUser(@PathVariable("projectCode") String projectCode){

        projectService.deleteById(projectCode);

        return "redirect:/project/create";
    }


}
