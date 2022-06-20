package ui;

import config.HibernateConfig;
import domains.task.TaskEntity;
import dto.auth.Session;
import dto.project.ProjectColumnDTO;
import dto.project.ProjectCreateDTO;
import dto.project.ProjectDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import dto.task.TaskCreateDTO;
import dto.task.TaskDTO;
import dto.task.TaskMemberCreateDTO;
import mappers.ApplicationContextHolder;
import pdp.uz.baseUtil.BaseUtils;
import pdp.uz.baseUtil.Colors;
import services.ProjectService;
import services.TaskService;
import services.UserService;

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
    TaskService taskService = new TaskService();
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

        main(args);
    }

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

                case "1" -> boardUI.editTask();
                case "2" -> boardUI.showTaskDetails();
                case "3" -> boardUI.addMemberToTask();
                default -> BaseUtils.println("Main page");
            }
        }
    }

    private void addMemberToTask() {
        TaskMemberCreateDTO taskMemberCreateDTO = TaskMemberCreateDTO.builder()
                .email(BaseUtils.readText("email ? "))
                .taskId(Long.valueOf(BaseUtils.readText("taskId ? ")))
                .userId(Session.sessionUser.getId()).build();

        ResponseEntity<DataDTO<Boolean>> response = taskService.addTaskMember(taskMemberCreateDTO);
        print_response(response);
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

    private void showMyProjects() {
        ResponseEntity<DataDTO<List<ProjectDTO>>> response = userService.getProjectList(Session.sessionUser.getId());
        print_response(response);
        BaseUtils.println("Add project column -> 1");
        BaseUtils.println("Edit project column -> 2");
        BaseUtils.println("Show project details -> 3");
        BaseUtils.println("Go back -> any key");
        String option = BaseUtils.readText("?: ");

        switch (option) {
            case "1" -> addProjectColumn();
            case "2" -> editProjectColumn();
            case "3" -> showProjectDetails();
            default -> BaseUtils.println("Main page");
        }


    }

    private void showProjectDetails() {
        Long projectId = Long.valueOf(BaseUtils.readText("project id ? "));
        ResponseEntity<DataDTO<ProjectDTO>> response = projectService.getProjectInfo(projectId, Session.sessionUser.getId());

        print_response(response);

    }

    private void editProjectColumn() {


    }

    private void addProjectColumn() {
        ProjectColumnDTO projectColumnDTO = ProjectColumnDTO.builder()
                .project_id(Long.valueOf(BaseUtils.readText("project_id ? ")))
                .name(BaseUtils.readText("name of column ? "))
                .code(BaseUtils.readText("insert code ? "))
                .order(Long.valueOf(BaseUtils.readText("insert order ? ")))
                .build();
        System.out.println(Session.sessionUser.getId());
        ResponseEntity<DataDTO<Long>> response = projectService.addProjectColumn(projectColumnDTO, Session.sessionUser.getId());
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
        System.out.println("projectDTO " + projectDTO);

        ResponseEntity<DataDTO<Long>> response = projectService.addProject(projectDTO);
        print_response(response);
    }

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }

}