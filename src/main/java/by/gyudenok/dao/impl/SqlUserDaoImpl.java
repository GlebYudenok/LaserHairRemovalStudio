package by.gyudenok.dao.impl;

import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.Dao;
import by.gyudenok.dao.UserDao;
import by.gyudenok.entity.Role;
import by.gyudenok.entity.User;
import by.gyudenok.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlUserDaoImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(SqlUserDaoImpl.class);
    private static final String SQL_INSERT_QUERY = new String("INSERT INTO USER " +
            "(id,login,password,email,role) VALUES (?,?,?,?,?)");
    private static final String SQL_READ_BY_ID_QUERY = new String("SELECT *FROM USER WHERE id=?");
    private static final String SQL_DELETE_BY_ID_QUERY = new String("DELETE FROM USER WHERE id=?");
    private static final String SQL_UPDATE_BY_ID_QUERY = new String("UPDATE USER SET " +
            "login=?, password=?, email=?, role=? WHERE id=?");
    private static final String SQL_READ_BY_LOGIN_QUERY = new String("SELECT *FROM USER WHERE login=?");
    private static final String SQL_READ_BY_LOGIN_N_PASSWORD_QUERY = new String(
            "SELECT *FROM USER WHERE login=? AND password=?"
    );

    @Override
    public boolean create(User user) throws DaoException {
        int code = 0;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_INSERT_QUERY);
            ps.setString(1, user.getId());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getRole().name());

            code = ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        if(code > 0) {
            LOGGER.info("User was created successfully!");
            return true;
        }else {
            LOGGER.warn("Cannot insert data");
            throw new DaoException();
        }
    }

    @Override
    public User read(String id) throws DaoException {
        User user = null;
        try {
            PreparedStatement preparedStatement =
                    ConnectionPool.getInstance().getConnection()
                            .prepareStatement(SQL_READ_BY_ID_QUERY);

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        Role.valueOf(resultSet.getString("role").toUpperCase())
                );
            }
            preparedStatement.close();
            resultSet.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        if(user.getId() == null) {
            throw new NullPointerException();
        }
        return user;
    }

    @Override
    public int update(User entity) throws DaoException {
        int code = 0;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_UPDATE_BY_ID_QUERY);
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getRole().name());
            ps.setString(5, entity.getId());
            code = ps.executeUpdate();
            if (code > 0) {
                LOGGER.info("User was update successfully!");
            } else {
                LOGGER.warn("User was not found or can't update!");
                throw new DaoException();
            }
            ps.close();
        } catch (SQLException e) {
            throw new DaoException();
        }
        return code;
    }


    @Override
    public int delete(String id) throws DaoException {
        int code = 0;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_DELETE_BY_ID_QUERY);
            ps.setString(1, id);

            code = ps.executeUpdate();
            if (code > 0) {
                LOGGER.info("User was delete successfully!");
            } else {
                LOGGER.warn("User not found or can't delete!");
                throw new DaoException();
            }
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        return code;
    }

    @Override
    public List<User> readAll() throws DaoException {
        List<User> userList = null;
        try {
            Statement statement =
                    ConnectionPool.getInstance().
                            getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT *FROM USER"
            );

            userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(
                        new User(
                                resultSet.getString("id"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                Role.valueOf(resultSet.getString("role").toUpperCase())
                        )
                );
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        return userList;
    }

    @Override
    public User readByLogin(String login) throws DaoException {
        User user = null;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_READ_BY_LOGIN_QUERY);
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        Role.valueOf(resultSet.getString("role").toUpperCase())
                );
            }
            resultSet.close();
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        if(user.getId() == null) {
            throw new NullPointerException();
        }
        return user;
    }

    @Override
    public User readByLoginNPassword(String login, String password) throws DaoException {
        User user = null;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_READ_BY_LOGIN_N_PASSWORD_QUERY);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                user = new User(
                    rs.getString("id"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("email"),
                    Role.valueOf(rs.getString("role").toUpperCase())
                );
            }
        }catch (SQLException e) {
            throw new DaoException();
        }
        if(user.getId() == null) {
            LOGGER.warn("User not found");
            throw new NullPointerException();
        }
        return user;
    }
}
