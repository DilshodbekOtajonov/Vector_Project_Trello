package ui;

import domains.task.TaskEntity;
import dto.TaskCreateDTO;
import dto.TaskDTO;
import dto.auth.Session;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import mappers.ApplicationContextHolder;
import services.ProjectService;
import services.TaskService;
import services.UserService;
import uz.jl.BaseUtils;
import uz.jl.Colors;

import java.util.List;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/20/22 2:18 PM (Monday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class TaskUI {
    UserService userService = ApplicationContextHolder.getBean(UserService.class);

    TaskUI taskUi = new TaskUI();
    TaskService taskService = new TaskService();
    ProjectService projectService = ApplicationContextHolder.getBean(ProjectService.class);


    private void addTask() {
        TaskCreateDTO taskDTO = TaskCreateDTO.builder()
                .title(BaseUtils.readText("title ? "))
                .description(BaseUtils.readText("description ? "))
                .priority(BaseUtils.readText("priority ? "))
                .projectColumnId(Long.valueOf(BaseUtils.readText("projectColumnId ? ")))
                .createdBy(Session.sessionUser.getId()).build();
        String option;
        System.out.print("Choose level(default-MEDIUM): ");
        option = BaseUtils.readText("\n1.EASY\n2.MEDIUM\n3.HARD\n?: ");

        switch (option) {
            case "1" -> taskDTO.setLevel("EASY");
            case "2" -> taskDTO.setLevel("HARD");
            default -> taskDTO.setLevel("MEDIUM");
        }

        ResponseEntity<DataDTO<Long>> response = taskService.addTask(taskDTO);
        print_response(response);
    }

    private void showMyTasks() {
        ResponseEntity<DataDTO<List<TaskDTO>>> response = userService.getTaskList(Session.sessionUser.getId());
        print_response(response);

        if (response.getStatus() == 200) {
            BaseUtils.println("\n\n" + "Edit task -> 1");
            BaseUtils.println("task details -> 2");
            BaseUtils.println("Add member to task -> 3");
            BaseUtils.println("go back -> any other key");
            String choice = BaseUtils.readText("?:");
            switch (choice) {

                case "1" -> taskUi.editTask();
                case "2" -> taskUi.showTaskDetails();
                default -> BaseUtils.println("Main page");
            }
        }
    }
    private void showTaskDetails() {
        Long taskId = Long.valueOf(BaseUtils.readText("task id ? "));
        ResponseEntity<DataDTO<TaskDTO>> response = projectService.getTaskInfo(taskId, Session.sessionUser.getId());

        print_response(response);
    }
    private void editTask() {
        Long taskId = Long.valueOf(BaseUtils.readText("task id ? "));
        ResponseEntity<DataDTO<TaskEntity>> response = projectService.getTaskById(taskId);

        print_response(response);
    }

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }
}
