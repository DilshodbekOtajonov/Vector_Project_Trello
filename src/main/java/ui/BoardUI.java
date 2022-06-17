package ui;

import config.HibernateConfig;
import dto.auth.Session;
import uz.jl.BaseUtils;
import uz.jl.Colors;

import java.util.Objects;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 3:54 PM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class BoardUI {
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

    }
}
