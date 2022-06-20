package services;

import Dao.ProjectDAO;
import Dao.TaskDAO;
import com.google.gson.reflect.TypeToken;
import dto.TaskDTO;
import dto.project.ProjectCreateDTO;
import dto.project.ProjectDTO;
import dto.response.AppErrorDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import exceptions.DaoException;
import mappers.ApplicationContextHolder;
import uz.jl.BaseUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/18/22 2:51 PM (Saturday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class UserService {
    private static UserService userService;
    ProjectDAO projectDAO = ApplicationContextHolder.getBean(ProjectDAO.class);
    TaskDAO taskDAO = ApplicationContextHolder.getBean(TaskDAO.class);


    public static UserService getInstance() {
        if (Objects.isNull(userService))
            userService = new UserService();
        return userService;
    }

    public ResponseEntity<DataDTO<List<ProjectDTO>>> getProjectList(Long id) {
        try {
            String projectList = projectDAO.getProjectList(id);

            Type type = new TypeToken<ArrayList<ProjectDTO>>() {
            }.getType();


            ArrayList<ProjectDTO> result = BaseUtils.gson.fromJson(projectList, type);

            if (result.isEmpty())
                return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                        .friendlyMessage("You do not have any projects")
                        .build()), 404);

            return new ResponseEntity<>(new DataDTO<>(result), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage("Oops something went wrong")
                    .build()), 500);
        }

    }

    public ResponseEntity<DataDTO<List<TaskDTO>>> getTaskList(Long id) {
        try {
            String taskList = taskDAO.getTaskList(id);
            Type type = new TypeToken<ArrayList<TaskDTO>>() {
            }.getType();

            ArrayList<TaskDTO> result = BaseUtils.gson.fromJson(taskList, type);
            if (result.isEmpty())
                return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                        .friendlyMessage("You do not have any taska")
                        .build()), 404);

            return new ResponseEntity<>(new DataDTO<>(result), 200);

        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage("Oops something went wrong")
                    .build()), 500);
        }
    }
}
