package ui;

import dto.auth.Session;
import dto.project.ProjectColumnDTO;
import dto.project.ProjectDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import mappers.ApplicationContextHolder;
import services.ProjectService;
import services.UserService;
import uz.jl.BaseUtils;
import uz.jl.Colors;

import java.util.Objects;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/20/22 2:18 PM (Monday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class ProjectUI {
    static UserService userService = ApplicationContextHolder.getBean(UserService.class);
    private static ProjectUI projectUI = new ProjectUI();
    private static ProjectService projectService = ApplicationContextHolder.getBean(ProjectService.class);


    public static void projectWindow(Long projectId) {
        if (Objects.isNull(Session.sessionUser))
            return;
        System.out.println("========================Project Window=======================");
        ResponseEntity<DataDTO<ProjectDTO>> response = projectService.getProjectInfo(projectId, Session.sessionUser.getId());

        print_response(response);

        if (response.getStatus() == 200) {

            BaseUtils.println("Add project column -> 1");
            BaseUtils.println("Edit project column -> 3");
            BaseUtils.println("Go back -> 4");
            BaseUtils.println("Logout -> 5");
            BaseUtils.println("Quit -> q");
            String option = BaseUtils.readText("?: ");

            switch (option) {
                case "1" -> projectUI.addProjectColumn();
                case "3" -> projectUI.editProjectColumn();
                case "4" -> BoardUI.boardWindow();
                case "5" -> Session.setSessionUser(null);

                default -> BaseUtils.println("Wrong Choice", Colors.RED);

            }

        }
        projectWindow(projectId);

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

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }
}
