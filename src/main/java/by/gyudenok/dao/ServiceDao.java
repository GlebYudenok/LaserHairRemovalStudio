package by.gyudenok.dao;

import by.gyudenok.entity.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDao extends Dao<Service> {
    List<Service> readAll() throws SQLException;
    Service readByName(String name);
}
