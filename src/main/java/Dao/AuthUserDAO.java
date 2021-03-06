package Dao;

import config.HibernateConfig;
import dto.auth.AuthCreateDTO;
import dto.auth.AuthLoginDTO;
import exceptions.DaoException;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import uz.jl.BaseUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/17/22 12:00 PM (Friday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class AuthUserDAO {
    private static AuthUserDAO authUserDAO;

    public String login(AuthLoginDTO loginDTO) throws DaoException {

        String result;
        Session currentSession = HibernateConfig.getSessionFactory().getCurrentSession();

        currentSession.beginTransaction();
        try {
            CallableStatement callableStatement = currentSession.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{ ? = call hr.user_login(?,?)}"
                );
                function.registerOutParameter(1, Types.VARCHAR);
                function.setString(2, loginDTO.getUsername());
                function.setString(3, loginDTO.getPassword());
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getString(1);
            } catch (SQLException e) {
                System.out.println("first " + e.getMessage());
                throw new DaoException(e.getMessage());
            }

            return result;

        } catch (Exception e) {
            throw new DaoException(e.getCause().getLocalizedMessage());
        } finally {
            currentSession.getTransaction().commit();
            currentSession.close();
        }
    }


    public static AuthUserDAO getInstance() {
        if (Objects.isNull(authUserDAO))
            authUserDAO = new AuthUserDAO();
        return authUserDAO;
    }

    public Long register(String authCreateDTO) throws DaoException {
        Long result;
        Session currentSession = HibernateConfig.getSessionFactory().getCurrentSession();
        currentSession.beginTransaction();
        try {
            CallableStatement callableStatement = currentSession.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall("{? = call hr.user_create(?)}");
                function.registerOutParameter(1, Types.BIGINT);
                function.setString(2, authCreateDTO);
                function.execute();
                return function;
            });
            try {
                result=callableStatement.getLong(1);
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
            return result;
        }catch (Exception e){
            throw new DaoException(e.getCause().getLocalizedMessage());
        }finally {
            currentSession.getTransaction().commit();
            currentSession.close();
        }

    }
}

