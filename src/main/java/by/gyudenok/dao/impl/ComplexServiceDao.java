package by.gyudenok.dao.impl;

import by.gyudenok.exception.DaoException;

import java.sql.SQLException;

public interface ComplexServiceDao<T> {
    void create() throws ClassNotFoundException, SQLException;
    T read(String id) throws SQLException;
    int update(T entity, String serviceIds) throws SQLException;
    int delete(String id) throws SQLException, DaoException;
}
