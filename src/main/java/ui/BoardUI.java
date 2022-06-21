package ui;

import config.HibernateConfig;
import dto.auth.Session;
import dto.project.ProjectCreateDTO;
import dto.project.ProjectInfoDTO;
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

    static UserService userService = ApplicationContextHolder.getBean(UserService.class);
    ProjectService projectService = ApplicationContextHolder.getBean(ProjectService.class);
    static BoardUI boardUI = new BoardUI();


    public static void boardWindow() {
        if (Objects.isNull(Session.sessionUser))
            return;
        System.out.println("========================Board Window=======================");

        ResponseEntity<DataDTO<List<ProjectInfoDTO>>> response = userService.getProjectList(Session.sessionUser.getId());
        print_response(response);

        BaseUtils.println("Add project -> 1");
        BaseUtils.println("project window -> 2");
        BaseUtils.println("Show my tasks -> 3");
        BaseUtils.println("logout -> 4");
        BaseUtils.println("Quit -> q");
        String choice = BaseUtils.readText("?:");
        switch (choice) {

            case "1" -> boardUI.addProject();
            case "2" -> {
                Long projectId = Long.valueOf(BaseUtils.readText("project id ? "));
                ProjectUI.projectWindow(projectId);
            }
            case "3" -> TaskUI.showMyTasks();
            case "4" -> Session.sessionUser=null;
            case "q" -> {
                BaseUtils.println("Bye");
                HibernateConfig.shutdown();
                System.exit(0);
            }
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }

        boardWindow();
    }


    private void addProject() {
        ProjectCreateDTO projectDTO = ProjectCreateDTO.builder()
                .title(BaseUtils.readText("title ? "))
                .description(BaseUtils.readText("description ? "))
                .docPath(BaseUtils.readText("doc_path ? "))
                .createdBy(Session.sessionUser.getId())
                .build();

        ResponseEntity<DataDTO<Long>> response = projectService.addProject(projectDTO);
        print_response(response);
    }

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }

}