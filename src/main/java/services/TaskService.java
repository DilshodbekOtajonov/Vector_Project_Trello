package services;

import Dao.TaskDAO;
import dto.response.AppErrorDTO;
import dto.response.DataDTO;
import dto.response.ResponseEntity;
import dto.task.TaskCreateDTO;
import exceptions.DaoException;
import mappers.ApplicationContextHolder;
import pdp.uz.baseUtil.BaseUtils;

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
}
