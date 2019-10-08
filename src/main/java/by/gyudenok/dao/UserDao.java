package by.gyudenok.dao;

import by.gyudenok.entity.User;
import by.gyudenok.exception.DaoException;

import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> readAll() throws DaoException;
    User readByLogin(String login) throws DaoException;
    User readByLoginNPassword(String login, String password) throws DaoException;
}
