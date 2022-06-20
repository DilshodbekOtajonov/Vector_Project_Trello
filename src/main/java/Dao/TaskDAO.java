package Dao;

import config.HibernateConfig;
import domains.task.TaskEntity;
import exceptions.DaoException;
import org.hibernate.Session;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;


public class TaskDAO extends GenericDAO<TaskEntity> {
    private static TaskDAO taskDAO;

    public TaskDAO() {
    }

    public static TaskDAO getInstance() {
        if (Objects.isNull(taskDAO))
            taskDAO = new TaskDAO();
        return taskDAO;
    }

    public Long addTask(String taskDTO) throws DaoException {
        Long result = null;
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{? = call task.task_create(?)}");
                function.registerOutParameter(1, Types.BIGINT);
                function.setString(2, taskDTO);
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getLong(1);
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
            return result;
        } catch (Exception e) {
            throw new DaoException(e.getCause().getLocalizedMessage());
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
