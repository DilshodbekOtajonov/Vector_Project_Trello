package ui;

import config.HibernateConfig;
import dto.auth.Session;
import dto.project.ProjectCreateDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import dto.task.TaskCreateDTO;
import mappers.ApplicationContextHolder;
import pdp.uz.baseUtil.BaseUtils;
import pdp.uz.baseUtil.Colors;
import services.ProjectService;
import services.TaskService;

import java.util.Objects;

public class BoardUI {

    ProjectService projectService = ApplicationContextHolder.getBean(ProjectService.class);
    TaskService taskService = ApplicationContextHolder.getBean(TaskService.class);

    public static void main(String[] args) {
        if (Objects.isNull(Session.sessionUser))
            return;

        BoardUI boardUI = new BoardUI();
        BaseUtils.println("AddProject -> 1");
        BaseUtils.println("addTask -> 2");
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
            case "5" -> boardUI.logout();


            case "q" -> {
                BaseUtils.println("Bye");
                HibernateConfig.shutdown();
                System.exit(0);
            }
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }

    }

    private void logout() {

    }

    private void addTask() {
        TaskCreateDTO taskDTO = TaskCreateDTO.builder()
                .title(BaseUtils.readText("title ? "))
                .description(BaseUtils.readText("description ? "))
                //.level(BaseUtils.readText("level ? "))
                .priority(BaseUtils.readText("priority ? "))
                .projectColumnId(Long.valueOf(BaseUtils.readText("projectColumnId ? ")))
                .createdBy(Session.sessionUser.getId()).build();


        String option;
        System.out.print("Choose level(default-MEDIUM): ");
        option=BaseUtils.readText("\n1.EASY\n2.MEDIUM\n3.HARD\n?: ");


        switch (option){
            case "1" -> taskDTO.setLevel("EASY");
            case "2" -> taskDTO.setLevel("HARD");
            default -> taskDTO.setLevel("MEDIUM");
        }

        ResponseEntity<DataDTO<Long>> response = taskService.addTask(taskDTO);
        print_response(response);
    }

    private void showMyTasks() {


    }

    private void showMyProjects() {

    }

    private void addProject() {
        ProjectCreateDTO projectCreateDTO = ProjectCreateDTO.builder()
                .title(BaseUtils.readText("title ? "))
                .description(BaseUtils.readText("description ? "))
                .docPath(BaseUtils.readText("doc_path ? "))
                .createdBy(Session.sessionUser.getId())
                .build();

        System.out.println("projectDTO = " + projectCreateDTO);

        ResponseEntity<DataDTO<Long>> response = projectService.addProject(projectCreateDTO);
        print_response(response);
    }

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);

    }
}
