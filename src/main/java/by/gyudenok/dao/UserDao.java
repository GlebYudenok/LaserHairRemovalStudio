package by.gyudenok.dao;

import by.gyudenok.entity.User;
import by.gyudenok.exception.DaoException;

import java.util.List;

public interface UserDao<T> extends Dao<User> {
    List<T> readAll() throws DaoException;
    T readByLogin(String login) throws DaoException;
    T readByLoginNPassword(String login, String password) throws DaoException;
}
