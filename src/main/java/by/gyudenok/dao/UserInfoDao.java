package by.gyudenok.dao;

import by.gyudenok.entity.UserInfo;

import java.sql.SQLException;
import java.util.List;

public interface UserInfoDao extends Dao<UserInfo> {
    List<UserInfo> readAll() throws SQLException;
    UserInfo readByName(String name, String surname);
}
