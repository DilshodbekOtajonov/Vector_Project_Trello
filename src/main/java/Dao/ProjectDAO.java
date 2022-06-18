package Dao;

import com.google.gson.Gson;
import config.HibernateConfig;
import domains.project.ProjectEntity;
import dto.auth.ProjectDTO;
import exceptions.DaoException;
import org.hibernate.Session;
import pdp.uz.baseUtil.BaseUtils;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Objects;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 12:00 PM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class ProjectDAO extends GenericDAO<ProjectEntity> {
    private static ProjectDAO projectDAO;

    private ProjectDAO() {
    }

    public static ProjectDAO getInstance() {
        if (Objects.isNull(projectDAO))
            projectDAO = new ProjectDAO();
        return projectDAO;
    }

    public String getProjectList(Long id) throws DaoException {

        String result;
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{ ? = call project.project_list(?)}"
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

    public Long addProject(ProjectDTO projectDTO) throws DaoException {
        Long result = null;
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{? = call project.project_create(?)}");
                function.registerOutParameter(1, Types.BIGINT);
                function.setString(2, BaseUtils.gson.toJson(projectDTO));
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
