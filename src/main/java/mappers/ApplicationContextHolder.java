package mappers;

import Dao.AuthUserDAO;
import Dao.ProjectDAO;
import services.ProjectService;
import services.UserService;
import services.auth.AuthService;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/18/22 12:14 PM (Saturday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class ApplicationContextHolder {

    public static <T> T getBean(Class<T> clazz) {
        return switch (clazz.getSimpleName()) {
            case "AuthUserDAO" -> (T) AuthUserDAO.getInstance();
            case "AuthService" -> (T) AuthService.getInstance();
            case "UserService" -> (T) UserService.getInstance();
            case "ProjectService" -> (T) ProjectService.getInstance();
            case "ProjectDAO" -> (T) ProjectDAO.getInstance();
            default -> throw new RuntimeException("Bean not found");
        };
    }

    public static Object getBean(String beanName) {
        return switch (beanName) {
            case "AuthUserDAO" -> AuthUserDAO.getInstance();
            case "AuthService" -> AuthService.getInstance();
            case "UserService" -> UserService.getInstance();
            case "ProjectDAO" -> ProjectDAO.getInstance();
            default -> throw new RuntimeException("Bean not found");
        };
    }
}