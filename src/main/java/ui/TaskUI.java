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
    static UserService userService = ApplicationContextHolder.getBean(UserService.class);

   static TaskUI taskUi = new TaskUI();
    TaskService taskService = new TaskService();
    static ProjectService projectService = ApplicationContextHolder.getBean(ProjectService.class);


    public static void showMyTasks() {
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

    private static void showTaskDetails() {
        Long taskId = Long.valueOf(BaseUtils.readText("task id ? "));
        ResponseEntity<DataDTO<TaskDTO>> response = projectService.getTaskInfo(taskId, Session.sessionUser.getId());

        print_response(response);
    }

    private static void editTask() {
        Long taskId = Long.valueOf(BaseUtils.readText("task id ? "));
        ResponseEntity<DataDTO<TaskEntity>> response = projectService.getTaskById(taskId);

        print_response(response);
    }

    public static  void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }
}
