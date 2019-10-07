package by.gyudenok.dao;

import by.gyudenok.exception.DaoException;

public interface Dao<T> {
    boolean create(T entity) throws DaoException;
    T read(String id) throws DaoException;
    int update(T entity) throws DaoException;
    int delete(String id) throws DaoException;
}
