package by.gyudenok.dao;

import by.gyudenok.exception.DaoException;

import java.util.List;

public interface ComplexServiceDao<T> {
    boolean create(T entity) throws DaoException;
    T read(String id) throws DaoException;
    int update(T entity, String serviceIds) throws DaoException;
    int delete(String id) throws DaoException;
    List<T> readAll() throws DaoException;
    T readByName(String name) throws DaoException;
}
