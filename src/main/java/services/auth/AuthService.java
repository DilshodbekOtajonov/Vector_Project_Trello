package services.auth;

import Dao.AuthUserDAO;
import dto.auth.EmployeeDTO;
import dto.auth.AuthCreateDTO;
import dto.auth.AuthLoginDTO;
import dto.auth.Session;
import dto.auth.UserDTO;
import dto.response.AppErrorDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import exceptions.DaoException;
import mappers.ApplicationContextHolder;
import uz.jl.BaseUtils;

import java.util.Objects;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 10:10 AM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class AuthService {
    private static AuthService authService;

    AuthUserDAO authUserDAO = ApplicationContextHolder.getBean(AuthUserDAO.class);


    public ResponseEntity<DataDTO<UserDTO>> login(AuthLoginDTO authLoginDTO) {
        String login = null;
        try {
            login = authUserDAO.login(authLoginDTO);
            UserDTO userDTO = BaseUtils.gson.fromJson(login, UserDTO.class);
            Session.setSessionUser(userDTO);
            return new ResponseEntity<>(new DataDTO<>(userDTO), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(
                    new DataDTO<>(
                            AppErrorDTO.builder()
                                    .friendlyMessage(e.getMessage())
                                    .build()),
                    500);
        }

    }


    public static AuthService getInstance() {
        if (Objects.isNull(authService))
            authService = new AuthService();
        return authService;
    }

    public ResponseEntity<DataDTO<Long>>register(AuthCreateDTO authCreateDTO) {
        Long register = null;
        try {
            String authCreateJson = BaseUtils.gson.toJson(authCreateDTO);
            register = authUserDAO.register(authCreateJson);
            UserDTO build = UserDTO.builder()
                    .id(register)
                    .username(authCreateDTO.getUsername())
                    .employee(EmployeeDTO.builder()
                            .fullName(authCreateDTO.getEmployeeCreateDTO().getFullName())
                            .phoneNumber(authCreateDTO.getEmployeeCreateDTO().getPhoneNumber())
                            .email(authCreateDTO.getEmployeeCreateDTO().getEmail())
                            .build())
                    .build();
            Session.setSessionUser(build);
            return new ResponseEntity<>(new DataDTO<>(register), 200);

        } catch (DaoException e) {
            return new ResponseEntity<>(
                    new DataDTO<>(AppErrorDTO.builder().friendlyMessage(e.getMessage()).build()), 500);
        }


    }
}
