package ui;

import config.HibernateConfig;
import dto.TaskDTO;
import dto.auth.Session;
import dto.project.ProjectDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import mappers.ApplicationContextHolder;
import services.ProjectService;
import services.UserService;
import uz.jl.BaseUtils;
import uz.jl.Colors;

import java.util.List;
import java.util.Objects;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 3:54 PM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class BoardUI {

    UserService userService = ApplicationContextHolder.getBean(UserService.class);
    ProjectService projectService = ApplicationContextHolder.getBean(ProjectService.class);

    public static void main(String[] args) {
        if (Objects.isNull(Session.sessionUser))
            return;

        BoardUI boardUI = new BoardUI();
        BaseUtils.println("Add project -> 1");
        BaseUtils.println("Add Task -> 2");
        BaseUtils.println("show my projects -> 3");
        BaseUtils.println("Show my tasks -> 4");
        BaseUtils.println("logout -> 5");
        BaseUtils.println("Quit -> q");
        String choice = BaseUtils.readText("?:");
        switch (choice) {

            case "1" -> boardUI.addProject();
            case "2" -> boardUI.addTask();
            case "3" -> boardUI.showMyProjects();
            case "4" -> boardUI.showMyTasks();
            case "5" -> Session.sessionUser = null;
            case "q" -> {
                BaseUtils.println("Bye");
                HibernateConfig.shutdown();
                System.exit(0);
            }
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }
    }

    private void addTask() {

    }

    private void showMyTasks() {
        ResponseEntity<DataDTO<List<TaskDTO>>> response = userService.getTaskList(Session.sessionUser.getId());
        print_response(response);
    }

    private void showMyProjects() {
        ResponseEntity<DataDTO<List<ProjectDTO>>> response = userService.getProjectList(Session.sessionUser.getId());
        print_response(response);
    }

    private void addProject() {
        dto.ProjectDTO projectDTO = dto.ProjectDTO.builder()
                .title(BaseUtils.readText("title ? "))
                .description(BaseUtils.readText("description ? "))
                .docPath(BaseUtils.readText("doc_path ? "))
                .createdBy(Long.valueOf(BaseUtils.readText("createdBy ? ")))
                .build();

        System.out.println("projectDTO = " + projectDTO);

        ResponseEntity<DataDTO<Long>> response = projectService.addProject(projectDTO);
        print_response(response);
    }

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }
}
