package ui;

import dto.task.CommentCreateDTO;
import dto.task.TaskDTO;
import dto.auth.Session;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import dto.task.TaskMemberCreateDTO;
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
    private static final UserService userService = ApplicationContextHolder.getBean(UserService.class);

    private static final TaskService taskService = ApplicationContextHolder.getBean(TaskService.class);
    private static final TaskUI taskUI = new TaskUI();

    public static void showMyTasks() {


        ResponseEntity<DataDTO<List<TaskDTO>>> response = userService.getTaskList(Session.sessionUser.getId());
        print_response(response);

        if (response.getStatus() == 200) {
            BaseUtils.println("\n\n" + "Edit task -> 1");
            BaseUtils.println("task details -> 2");
            BaseUtils.println("go back ->3");
            String choice = BaseUtils.readText("?:");
            switch (choice) {

                case "1" -> taskUI.editTask();
                case "2" -> taskUI.showTaskDetails();
                case "3" -> BoardUI.boardWindow();
                default -> BaseUtils.println("Wrong Choice", Colors.RED);

            }
        } else BoardUI.boardWindow();
        showMyTasks();
    }

    private void addCommentToTask() {
        CommentCreateDTO commentCreateDTO = CommentCreateDTO.builder()
                .createdBy(Session.sessionUser.getId())
                .taskId(Long.valueOf(BaseUtils.readText("Task id? ")))
                .message(BaseUtils.readText("Message? "))
                .build();
        taskService.addCommentToTask(commentCreateDTO);
    }


    private void showTaskDetails() {

    }

    private void editTask() {
        BaseUtils.println("Add member to task -> 1 ");
        BaseUtils.println("Add comment to task -> 2 ");

        String choice = BaseUtils.readText("?:");
        switch (choice) {
            case "1" -> addMemberToTask();
            case "2" -> addCommentToTask();
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }
    }

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }

    private void addMemberToTask() {
        TaskMemberCreateDTO taskMemberCreateDTO = TaskMemberCreateDTO.builder()
                .email(BaseUtils.readText("email ? "))
                .taskId(Long.valueOf(BaseUtils.readText("taskId ? ")))
                .userId(Session.sessionUser.getId()).build();

        ResponseEntity<DataDTO<String>> response = taskService.addTaskMember(taskMemberCreateDTO);
        print_response(response);
    }
}
