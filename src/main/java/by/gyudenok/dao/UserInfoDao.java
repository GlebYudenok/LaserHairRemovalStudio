package by.gyudenok.dao;

import by.gyudenok.entity.UserInfo;
import by.gyudenok.exception.DaoException;

import java.util.List;

public interface UserInfoDao<T> extends Dao<UserInfo> {
    List<T> readAll() throws DaoException;
    T readByName(String name, String surname) throws DaoException;
}
