package Dao;

import config.HibernateConfig;
import domains.task.TaskEntity;
import exceptions.DaoException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Objects;

public class TaskDAO {
    private static TaskDAO taskDAO;

    private TaskDAO() {
    }

    public static TaskDAO getInstance() {
        if (Objects.isNull(taskDAO))
            taskDAO = new TaskDAO();
        return taskDAO;
    }

    public String getTaskList(Long id) throws DaoException {
        String result;
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{ ? = call task.task_list(?)}"
                );
                function.registerOutParameter(1, Types.VARCHAR);
                function.setLong(2, id);
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getString(1);
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

    public String getTaskById(Long taskId) throws DaoException {
        String result;
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{ ? = call task.get_task_by_id(?)}"
                );
                function.registerOutParameter(1, Types.VARCHAR);
                function.setLong(2, taskId);
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException(e.getMessage());
            }


        } catch (Exception e) {
            throw new DaoException(e.getCause().getLocalizedMessage());
        } finally {
            session.getTransaction().commit();
            session.close();
        }
        return result;
    }


    public String getTaskInfo(Long taskId, Long userId) throws DaoException {
        String result;
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{ ? = call task.task_details(?,?)}"
                );
                function.registerOutParameter(1, Types.VARCHAR);
                function.setLong(2, taskId);
                function.setLong(3, userId);
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException(e.getMessage());
            }


        } catch (Exception e) {
            throw new DaoException(e.getCause().getLocalizedMessage());
        } finally {
            session.getTransaction().commit();
            session.close();
        }
        return result;
    }

}


