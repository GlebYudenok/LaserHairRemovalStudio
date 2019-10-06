package by.gyudenok.dao;

import by.gyudenok.entity.UserInfo;

import java.util.List;

public interface UserInfoDao extends Dao<UserInfo> {
    List<UserInfo> readAll();
    UserInfo readByName(String name, String surname);
}
