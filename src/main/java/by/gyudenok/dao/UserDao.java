package by.gyudenok.dao;

import by.gyudenok.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> readAll() throws SQLException;
    User readByLogin(String login) throws SQLException;
}
