package com.domain.controller;

import com.domain.service.ProjectService;
import com.domain.service.RoleService;
import com.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {

        RoleService roleService;
        UserService userService;
        ProjectService projectService;

        public ManagerController(RoleService roleService, UserService userService, ProjectService projectService) {
            this.roleService = roleService;
            this.userService = userService;
            this.projectService = projectService;
        }

        @GetMapping("/project-status")
        public String projectStatus(Model model){
            model.addAttribute("projects",projectService.findAll());
            return "/manager/project-status";
        }
}
