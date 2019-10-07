package by.gyudenok.dao;

import by.gyudenok.entity.Service;
import by.gyudenok.exception.DaoException;

import java.util.List;

public interface ServiceDao extends Dao<Service> {
    List<Service> readAll() throws DaoException;
    Service readByName(String name) throws DaoException;
}
