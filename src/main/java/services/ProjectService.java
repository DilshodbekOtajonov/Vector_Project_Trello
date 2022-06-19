package services;

import Dao.ProjectDAO;
import dto.project.ProjectCreateDTO;
import dto.project.ProjectDTO;
import dto.response.AppErrorDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import exceptions.DaoException;
import mappers.ApplicationContextHolder;

import java.util.Objects;

public class ProjectService {
    private static ProjectService projectService;

    ProjectDAO projectDAO = ApplicationContextHolder.getBean(ProjectDAO.class);

    public ResponseEntity<DataDTO<Long>> addProject(ProjectCreateDTO projectCreateDTO) {
        Long addProject = null;
        try {
            addProject = projectDAO.addProject(projectCreateDTO);
            return new ResponseEntity<>(new DataDTO<>(addProject), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage()).build()), 400);
        }

    }

    public static ProjectService getInstance() {
        if (Objects.isNull(projectService))
            projectService = new ProjectService();
        return projectService;
    }
}