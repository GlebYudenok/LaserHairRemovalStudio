package by.gyudenok.dao.impl;

import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.UserDao;
import by.gyudenok.entity.Role;
import by.gyudenok.entity.User;
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

    @Override
    public boolean create(User user) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_INSERT_QUERY);
        ps.setString(1, user.getId());
        ps.setString(2, user.getLogin());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getEmail());
        ps.setString(5, user.getRole().name());

        boolean ex = ps.execute();
        if(ex == false) {
            LOGGER.warn("Cannot create user");
        }else {
            LOGGER.info("User was created successfully!");
        }
        return ex;
    }

    @Override
    public User read(String id) throws SQLException {

        PreparedStatement preparedStatement =
                ConnectionPool.getInstance().getConnection()
                .prepareStatement(SQL_READ_BY_ID_QUERY);

        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getString("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    Role.valueOf(resultSet.getString("role").toUpperCase())
            );
        }
        return user;
    }

    @Override
    public int update(User entity) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_UPDATE_BY_ID_QUERY);
        ps.setString(1, entity.getLogin());
        ps.setString(2, entity.getPassword());
        ps.setString(3, entity.getEmail());
        ps.setString(4, entity.getRole().name());
        ps.setString(5, entity.getId());
        int code = ps.executeUpdate();
        if(code > 0) {
            LOGGER.info("User was update successfully!");
        }else {
            LOGGER.warn("User was not found or can't update!");
        }
        return code;
    }


    @Override
    public int delete(String id) throws SQLException {

        PreparedStatement ps = ConnectionPool.getInstance()
                        .getConnection().prepareStatement(SQL_DELETE_BY_ID_QUERY);
        ps.setString(1, id);
        ps.execute();
        int code = ps.executeUpdate();
        if(code > 0) {
            LOGGER.info("User was delete successfully!");
        } else {
            LOGGER.warn("User not found or can't delete!");
        }
        return code;
    }

    @Override
    public List<User> readAll() throws SQLException {

        Statement statement =
                ConnectionPool.getInstance().
                        getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT *FROM USER"
        );

        List<User> userList = new ArrayList<>();

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
        return userList;
    }

    @Override
    public User readByLogin(String login) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_READ_BY_LOGIN_QUERY);
        ps.setString(1, login);
        ResultSet resultSet = ps.executeQuery();
        User user = null;
        if(resultSet.next()) {
            user = new User(
                    resultSet.getString("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    Role.valueOf(resultSet.getString("role"))
            );
        }
        return user;
    }
}