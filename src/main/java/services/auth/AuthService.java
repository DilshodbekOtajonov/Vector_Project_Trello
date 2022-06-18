package services.auth;

import Dao.AuthUserDAO;
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
            return new ResponseEntity<>(new DataDTO<>(userDTO),200);
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
}
