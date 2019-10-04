package by.gyudenok.dao;

import by.gyudenok.exception.DaoException;

import java.sql.SQLException;

public interface Dao<T> {
    void create() throws ClassNotFoundException, SQLException;
    T read(String id) throws SQLException;
    int update(T entity) throws SQLException;
    int delete(String id) throws SQLException, DaoException;
}
