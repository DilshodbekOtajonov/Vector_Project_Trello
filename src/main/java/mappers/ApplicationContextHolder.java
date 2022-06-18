package mappers;

import Dao.AuthUserDAO;
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
            default -> throw new RuntimeException("Bean not found");
        };
    }

    public static Object getBean(String beanName) {
        return switch (beanName) {
            case "AuthUserDAO" -> AuthUserDAO.getInstance();
            case "AuthService" -> AuthService.getInstance();
            default -> throw new RuntimeException("Bean not found");
        };
    }
}
