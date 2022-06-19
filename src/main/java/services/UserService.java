package services;

import Dao.ProjectDAO;
import dto.project.ProjectDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import exceptions.DaoException;
import mappers.ApplicationContextHolder;

import java.util.List;
import java.util.Objects;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/18/22 2:51 PM (Saturday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class UserService {
    private static UserService userService;
    ProjectDAO projectDAO= ApplicationContextHolder.getBean(ProjectDAO.class);


    public static UserService getInstance() {
        if (Objects.isNull(userService))
            userService = new UserService();
        return userService;
    }

    public ResponseEntity<DataDTO<List<ProjectDTO>>> getProjectList(Long id) {
        try {
            System.out.println(projectDAO.getProjectList(id));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
