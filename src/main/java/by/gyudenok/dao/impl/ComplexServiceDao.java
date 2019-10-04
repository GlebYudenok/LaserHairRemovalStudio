package by.gyudenok.dao.impl;

import by.gyudenok.dao.Dao;
import by.gyudenok.entity.ComplexService;
import by.gyudenok.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface ComplexServiceDao<T> {
    void create() throws ClassNotFoundException, SQLException;
    T read(String id) throws SQLException;
    int update(T entity, String serviceIds) throws SQLException;
    int delete(String id) throws SQLException, DaoException;
}
