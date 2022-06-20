package services;

import Dao.ProjectDAO;
import Dao.TaskDAO;
import domains.task.TaskEntity;
import dto.task.TaskDTO;
import dto.auth.Session;
import dto.project.ProjectColumnDTO;
import dto.project.ProjectCreateDTO;
import dto.project.ProjectDTO;
import dto.response.AppErrorDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import exceptions.DaoException;
import mappers.ApplicationContextHolder;
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
                    .friendlyMessage(e.getMessage()).build()), 400);
        }

    }

    public ResponseEntity<DataDTO<TaskEntity>> getTaskById(Long taskId) {
        try {
            String taskJson = taskDAO.getTaskById(taskId);
            TaskEntity taskEntity = BaseUtils.gson.fromJson(taskJson, TaskEntity.class);
            System.out.println(taskEntity);
//            return new ResponseEntity<>(new DataDTO<>(taskDAO.getTaskById(taskId)));

        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage())
                    .build()), 500);
        }
        return null;
    }

    public ResponseEntity<DataDTO<TaskDTO>> getTaskInfo(Long taskId, Long userId) {
        try {
            String taskInfoJson = taskDAO.getTaskInfo(taskId, userId);
            TaskDTO taskDTO = BaseUtils.gson.fromJson(taskInfoJson, TaskDTO.class);
            return new ResponseEntity<>(new DataDTO<>(taskDTO), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getLocalizedMessage())
                    .build()), 500);
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
}