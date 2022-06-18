package ui;

import config.HibernateConfig;
import dto.auth.Session;
import pdp.uz.baseUtil.BaseUtils;
import pdp.uz.baseUtil.Colors;

import java.util.Objects;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/16/22 5:29 PM (Thursday)
 * VectorGroupProject/IntelliJ IDEA
 */
public final class AuthUI {


    public static void main(String[] args) {

        AuthUI authUI = new AuthUI();

        if (Objects.isNull(Session.sessionUser)) {
            BaseUtils.println("Login -> 1");
            BaseUtils.println("Register -> 2");
            BaseUtils.println("Quit -> q");

            String choice = BaseUtils.readText("?:");
            switch (choice) {
                case "1" -> authUI.login();
                case "2" -> authUI.register();
                case "q" -> {
                    BaseUtils.println("Bye");
                    HibernateConfig.shutdown();
                    System.exit(0);
                }
                default -> BaseUtils.println("Wrong Choice", Colors.RED);
            }
        } else BoardUI.main(args);


        main(args);

    }

    private void register() {

    }

    private void login() {

    }
}
