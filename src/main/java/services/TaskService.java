package services;

import Dao.TaskDAO;
import dto.task.CommentCreateDTO;
import dto.task.TaskCreateDTO;
import dto.response.AppErrorDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import dto.task.TaskMemberCreateDTO;
import exceptions.DaoException;
import mappers.ApplicationContextHolder;
import ui.BoardUI;
import uz.jl.BaseUtils;

import java.util.Objects;

public class TaskService {
    private static TaskService taskService;
    TaskDAO taskDAO = ApplicationContextHolder.getBean(TaskDAO.class);

    public static TaskService getInstance() {
        if (Objects.isNull(taskService))
            taskService = new TaskService();
        return taskService;
    }

    public ResponseEntity<DataDTO<Long>> addTask(TaskCreateDTO taskCreateDTO) {
        Long addTask = null;
        try {
            String response = BaseUtils.gson.toJson(taskCreateDTO);
            addTask = taskDAO.addTask(response);
            return new ResponseEntity<>(new DataDTO<>(addTask), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage()).build()), 400);
        }
    }

    public ResponseEntity<DataDTO<String>> addTaskMember(TaskMemberCreateDTO taskMemberCreateDTO) {
        try {
            String taskMemberCreateJson = BaseUtils.gson.toJson(taskMemberCreateDTO);
            Boolean addTaskMember = taskDAO.addTaskMember(taskMemberCreateJson);
            if (addTaskMember)
                return new ResponseEntity<>(new DataDTO<>("member added successfully"), 200);
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage("oops something went wrong")
                    .build()), 400);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage()).build()), 400);
        }
    }

    public ResponseEntity<DataDTO<String>> addCommentToTask(CommentCreateDTO commentCreateDTO) {
        try {
            String commentCreateJson = BaseUtils.gson.toJson(commentCreateDTO);
            Long result = taskDAO.addComment(commentCreateJson);
            if (Objects.nonNull(result))
                return new ResponseEntity<>(new DataDTO<>("comment added successfully"), 200);
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage("oops something went wrong")
                    .build()), 400);

        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getLocalizedMessage())
                    .build()), 500);
        }
    }
}
