package com.domain.bootstrap;

import com.domain.dto.ProjectDTO;
import com.domain.dto.RoleDTO;
import com.domain.dto.TaskDTO;
import com.domain.dto.UserDTO;
import com.domain.enums.Gender;
import com.domain.enums.Status;
import com.domain.service.ProjectService;
import com.domain.service.RoleService;
import com.domain.service.TaskService;
import com.domain.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class DataGenerator implements CommandLineRunner {

    RoleService roleService;
    UserService userService;
    ProjectService projectService;
    TaskService taskService;

    public DataGenerator(RoleService roleService, UserService userService, ProjectService projectService, TaskService taskService) {
        this.roleService = roleService;
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {

        RoleDTO adminRole=new RoleDTO(1L,"Admin");
        RoleDTO managerRole=new RoleDTO(2L,"Manager");
        RoleDTO employeeRole=new RoleDTO(3L,"Employee");

        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);

        UserDTO user1 = new UserDTO("John", "Kesy",
                "john@gmail.com", "Abc1", true, "7459684532", managerRole, Gender.MALE);
        UserDTO user5 = new UserDTO("Mike", "Smith",
                "mike@gmail.com", "Abc2", true, "7459684532", adminRole, Gender.MALE);
        UserDTO user2 = new UserDTO("Delisa",
                "Norre", "delisa@gmail.com", "123", true, "8567412358", managerRole, Gender.FEMALE);
        UserDTO user3 = new UserDTO("Craig", "Jark",
                "craig@gmail.com", "Abc3", true, "7777775566", employeeRole, Gender.MALE);
        UserDTO user4 = new UserDTO("Shaun",
                "Hayns", "shaun@gmail.com", "Abc4", true, "3256987412", managerRole, Gender.MALE);
        UserDTO user6 = new UserDTO("Elizebeth",
                "Loren", "elizebeth@gmail.com", "Abc4", true, "5306987412", employeeRole, Gender.FEMALE);
        UserDTO user7 = new UserDTO("Maria",
                "Ada", "maria@gmail.com", "Abc4", true, "9996987412", employeeRole, Gender.FEMALE);
        UserDTO user8 = new UserDTO("Bill",
                "Matt", "bill@gmail.com", "Abc4", true, "8881239846", employeeRole, Gender.MALE);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);


        ProjectDTO project1 = new ProjectDTO("Spring MVC","PR001",user1, LocalDate.now(),LocalDate.now().plusDays(25),"Creating Controllers", Status.OPEN);
        ProjectDTO project2 = new ProjectDTO("Spring ORM","PR002",user2, LocalDate.now(),LocalDate.now().plusDays(10),"Creating Database", Status.IN_PROGRESS);
        ProjectDTO project3 = new ProjectDTO("Spring Container","PR003",user1, LocalDate.now(),LocalDate.now().plusDays(32),"Creating Container", Status.IN_PROGRESS);

        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);


        TaskDTO task1 = new TaskDTO(project1,user8,"Controller","Request Mapping",Status.IN_PROGRESS,LocalDate.now().minusDays(4));
        TaskDTO task2 = new TaskDTO(project3,user3,"Configuration","Database Connnection",Status.COMPLETE,LocalDate.now().minusDays(12));
        TaskDTO task3 = new TaskDTO(project3,user6,"Mapping","One-To-Many",Status.IN_PROGRESS,LocalDate.now().minusDays(8));
        TaskDTO task4 = new TaskDTO(project2,user7,"Dependency Injection","Autowired",Status.IN_PROGRESS,LocalDate.now().minusDays(20));
        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);
    }
}
