package services;

import Dao.ProjectDAO;
import Dao.TaskDAO;
import dto.auth.Session;
import dto.project.ProjectColumnDTO;
import dto.project.ProjectCreateDTO;
import dto.project.ProjectDTO;
import dto.response.AppErrorDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import exceptions.DaoException;
import config.ApplicationContextHolder;
import uz.jl.BaseUtils;

import java.util.Objects;

public class ProjectService {
    private static ProjectService projectService;
    TaskDAO taskDAO = ApplicationContextHolder.getBean(TaskDAO.class);

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


    public ResponseEntity<DataDTO<Long>> addProjectColumn(ProjectColumnDTO projectColumnDTO, Long userId) {
        Long addProjectColumn = null;

        try {
            addProjectColumn = projectDAO.addProjectColumn(projectColumnDTO, userId);
            return new ResponseEntity<>(new DataDTO<>(addProjectColumn), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage()).build()), 500);
        }

    }





    public ResponseEntity<DataDTO<ProjectDTO>> getProjectInfo(Long projectId, Long userId) {
        try {
            String projectInfoJson = projectDAO.getProjectInfo(projectId, userId);
            ProjectDTO projectDTO = BaseUtils.gson.fromJson(projectInfoJson, ProjectDTO.class);
            Session.setSessionProject(projectDTO);
            return new ResponseEntity<>(new DataDTO<>(projectDTO),200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getLocalizedMessage())
                    .build()), 500);
        }
    }

    public ResponseEntity editProjectColumn(ProjectColumnDTO projectColumnDTO) {

        Long editProjectColumn = null;

        try {
            editProjectColumn = Long.valueOf(projectDAO.editProjectColumn(projectColumnDTO));
            return new ResponseEntity<>(new DataDTO<>(editProjectColumn), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage()).build()), 400);
        }

    }
}