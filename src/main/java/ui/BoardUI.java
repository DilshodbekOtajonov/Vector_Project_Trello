package ui;

import config.HibernateConfig;
import dto.auth.ProjectDTO;
import dto.auth.Session;
import dto.auth.UserDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import mappers.ApplicationContextHolder;
import pdp.uz.baseUtil.BaseUtils;
import pdp.uz.baseUtil.Colors;
import services.ProjectService;
import services.auth.AuthService;

import java.util.Map;
import java.util.Objects;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 3:54 PM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class BoardUI {

    ProjectService projectService=ApplicationContextHolder.getBean(ProjectService.class);

    public static void main(String[] args) {
        if (Objects.isNull(Session.sessionUser))
            return;

        BoardUI boardUI = new BoardUI();
        BaseUtils.println("Add project -> 1");
        BaseUtils.println("show my projects -> 2");
        BaseUtils.println("Show my tasks -> 3");
        BaseUtils.println("logout -> 4");
        BaseUtils.println("Quit -> q");
        String choice = BaseUtils.readText("?:");
        switch (choice) {

            case "1" -> boardUI.addProject();
            case "2" -> boardUI.showMyProjects();
            case "3" -> boardUI.showMyTasks();

            case "q" -> {
                BaseUtils.println("Bye");
                HibernateConfig.shutdown();
                System.exit(0);
            }
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }

    }

    private void showMyTasks() {


    }

    private void showMyProjects() {

    }

    private void addProject() {
        ProjectDTO projectDTO = ProjectDTO.builder()
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
