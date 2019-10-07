package by.gyudenok.dao;

import by.gyudenok.entity.UserInfo;
import by.gyudenok.exception.DaoException;

import java.util.List;

public interface UserInfoDao extends Dao<UserInfo> {
    List<UserInfo> readAll() throws DaoException;
    UserInfo readByName(String name, String surname) throws DaoException;
}
