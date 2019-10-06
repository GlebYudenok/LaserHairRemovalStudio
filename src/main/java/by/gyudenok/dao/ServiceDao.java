package by.gyudenok.dao;

import by.gyudenok.entity.Service;

import java.util.List;

public interface ServiceDao extends Dao<Service> {
    List<Service> readAll();
    Service readByName(String name);
}
