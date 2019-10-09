package by.gyudenok.dao;

import by.gyudenok.entity.Service;
import by.gyudenok.exception.DaoException;

import java.util.List;

public interface ServiceDao<T> extends Dao<Service> {
    List<T> readAll() throws DaoException;
    T readByName(String name) throws DaoException;
}
