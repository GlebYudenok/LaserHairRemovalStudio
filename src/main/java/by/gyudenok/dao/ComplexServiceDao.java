package by.gyudenok.dao;

import by.gyudenok.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface ComplexServiceDao<T> {
    boolean create(T entity) throws ClassNotFoundException, SQLException;
    T read(String id) throws SQLException;
    int update(T entity, String serviceIds) throws SQLException;
    int delete(String id) throws SQLException, DaoException;
    List<T> readAll();
    T readByName(String name);
}
