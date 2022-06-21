package ui;

import config.HibernateConfig;

import dto.auth.UserDTO;
import dto.auth.*;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import config.ApplicationContextHolder;
import services.auth.AuthService;
import uz.jl.BaseUtils;
import uz.jl.Colors;

import java.util.Objects;


/**
 * @author "Otajonov Dilshodbek
 * @since 6/16/22 5:29 PM (Thursday)
 * VectorGroupProject/IntelliJ IDEA
 */
public final class AuthUI {

    AuthService authService = ApplicationContextHolder.getBean(AuthService.class);
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
        } else BoardUI.boardWindow();


        main(args);

    }

    private void register() {

        AuthCreateDTO authCreateDTO = AuthCreateDTO.builder()
                .username(BaseUtils.readText("username ?"))
                .password(BaseUtils.readText("password ? "))
                .employeeCreateDTO(EmployeeCreateDTO.builder()
                        .fullName(BaseUtils.readText("fullName ? "))
                        .phoneNumber(BaseUtils.readText("phoneNumber ? "))
                        .email(BaseUtils.readText("email ? "))
                        .build())
                .build();


        String option;
        System.out.println("Choose language(default-RU): ");
        option=BaseUtils.readText("\n1.EN\n2.RU\n3.UZ\n?: ");


        switch (option){
            case "1" -> authCreateDTO.setLanguage("EN");
            case "2" -> authCreateDTO.setLanguage("UZ");
            default -> authCreateDTO.setLanguage("RU");
        }

        ResponseEntity<DataDTO<Long>> response = authService.register(authCreateDTO);
        print_response(response);
    }

    private void login() {
        AuthLoginDTO authLoginDTO = AuthLoginDTO.builder()
                .username(BaseUtils.readText("username ? "))
                .password(BaseUtils.readText("password ? "))
                .build();

        ResponseEntity<DataDTO<UserDTO>> response = authService.login(authLoginDTO);
        print_response(response);

    }
    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);

    }
}
