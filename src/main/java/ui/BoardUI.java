package ui;

import config.HibernateConfig;
import dto.TaskDTO;
import dto.auth.Session;
import dto.project.ProjectCreateDTO;
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
    static BoardUI boardUI = new BoardUI();
    public static void main(String[] args) {
        if (Objects.isNull(Session.sessionUser))
            return;

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

        BaseUtils.println("\n\n"+"Edit task -> 1");
        BaseUtils.println("go back -> any key");
        String choice = BaseUtils.readText("?:");
        switch (choice) {

            case "1" -> boardUI.editTask();

            default -> BaseUtils.println("Main page");
        }
    }

    private void editTask() {




    }

    private void showMyProjects() {
        ResponseEntity<DataDTO<List<ProjectCreateDTO>>> response = userService.getProjectList(Session.sessionUser.getId());
        print_response(response);

    }

    private void addProject() {
        ProjectCreateDTO projectDTO = ProjectCreateDTO.builder()
                .title(BaseUtils.readText("title ? "))
                .description(BaseUtils.readText("description ? "))
                .docPath(BaseUtils.readText("doc_path ? "))
                .createdBy(Session.sessionUser.getId())
                .build();
        System.out.println(Session.sessionUser.getId());
        System.out.println("projectDTO "+projectDTO);

        ResponseEntity<DataDTO<Long>> response = projectService.addProject(projectDTO);
        print_response(response);
    }

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }
}
