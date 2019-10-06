package by.gyudenok.dao.impl;

import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.UserInfoDao;
import by.gyudenok.entity.Gender;
import by.gyudenok.entity.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SqlUserInfoDaoImpl implements UserInfoDao {

    private static final Logger LOGGER = LogManager.getLogger(SqlUserInfoDaoImpl.class);
    private static final String SQL_INSERT_QUERY = new String("INSERT INTO USER_INFO " +
            "(user_id,name,surname,avatar_link,birth_date,phone_number,gender) VALUES (?,?,?,?,?,?,?)");
    private static final String SQL_READ_BY_ID_QUERY = new String("SELECT *FROM USER_INFO WHERE USER_ID = ?");
    private static final String SQL_DELETE_BY_ID_QUERY = new String("DELETE FROM USER_INFO WHERE USER_ID = ?");
    private static final String SQL_UPDATE_BY_ID_QUERY = new String("UPDATE USER_INFO SET name=?," +
            "surname=?, avatar_link=?, birth_date=?, phone_number=?, gender=? WHERE user_id=?");
    private static final String SQL_READ_ALL_QUERY = new String("SELECT *FROM USER_INFO");
    private static final String SQL_READ_BY_NAME_QUERY = new String("SELECT *FROM USER_INFO WHERE name=? AND surname=?");

    @Override
    public boolean create(UserInfo userInfo) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_INSERT_QUERY);
        ps.setString(1, userInfo.getUserId());
        ps.setString(2, userInfo.getName());
        ps.setString(3, userInfo.getSurname());
        ps.setString(4, userInfo.getAvatarLink());
        Timestamp timestamp = new Timestamp(userInfo.getDateOfBirth().getTime().getTime());
        ps.setTimestamp(5, timestamp);
        ps.setString(6, userInfo.getPhoneNumber());
        ps.setString(7, userInfo.getGender().name());

        int code = ps.executeUpdate();
        ps.close();
        if(code > 0) {
            LOGGER.info("User info was inserted successfully!");
            return true;
        }else {
            LOGGER.warn("Cannot insert data");
            return false;
        }
    }

    @Override
    public UserInfo read(String userId) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_READ_BY_ID_QUERY);
        ps.setString(1, userId);
        ResultSet resultSet = ps.executeQuery();

        UserInfo userInfo = null;

        if(resultSet.next()) {
            Calendar calendar = Calendar.getInstance();
            Timestamp timestamp = resultSet.getTimestamp("birth_date");
            java.util.Date date = new Date(timestamp.getTime());
            calendar.setTime(date);
            userInfo = new UserInfo(
                    resultSet.getString("avatar_link"),
                    resultSet.getString("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    calendar,
                    resultSet.getString("phone_number"),
                    Gender.valueOf(resultSet.getString("gender").toUpperCase())
            );
        }
        resultSet.close();
        ps.close();
        return userInfo;
    }

    @Override
    public int update(UserInfo userInfo) throws SQLException {
        PreparedStatement ps = ConnectionPool.getInstance()
                .getConnection().prepareStatement(SQL_UPDATE_BY_ID_QUERY);
        Timestamp ts = new Timestamp(userInfo.getDateOfBirth().getTime().getTime());
        ps.setString(1, userInfo.getName());
        ps.setString(2, userInfo.getSurname());
        ps.setString(3, userInfo.getAvatarLink());
        ps.setTimestamp(4, ts);
        ps.setString(5, userInfo.getPhoneNumber());
        ps.setString(6, userInfo.getGender().name());
        ps.setString(7, userInfo.getUserId());
        int code = ps.executeUpdate();
        if(code > 0) {
            LOGGER.info("User info was update successfully!");
        }else {
            LOGGER.warn("User info not found or can't update!");
        }
        ps.close();
        return code;
    }

    @Override
    public int delete(String userId) throws SQLException {
        PreparedStatement ps= ConnectionPool.getInstance()
                        .getConnection().prepareStatement (SQL_DELETE_BY_ID_QUERY);
        ps.setString(1, userId);
        int code = ps.executeUpdate();
        if(code > 0) {
            LOGGER.info("User info was delete successfully!");
        } else {
            LOGGER.warn("User info not found or can't delete!");
        }
        ps.close();
        return code;
    }

    @Override
    public List<UserInfo> readAll() throws SQLException {
        Statement statement = ConnectionPool.getInstance()
                .getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_READ_ALL_QUERY);
        List<UserInfo> userInfos = new ArrayList<>();
        Timestamp timestamp;
        while (resultSet.next()) {
            Calendar calendar = Calendar.getInstance();
            timestamp = new Timestamp(resultSet.getTimestamp("birth_date").getTime());
            calendar.setTime(timestamp);
            userInfos.add(
                    new UserInfo(
                            resultSet.getString("avatar_link"),
                            resultSet.getString("user_id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            calendar,
                            resultSet.getString("phone_number"),
                            Gender.valueOf(resultSet.getString("gender").toUpperCase())
                    )
            );
        }
        resultSet.close();
        statement.close();
        return userInfos;
    }

    @Override
    public UserInfo readByName(String name, String surname) {
        return null;
    }
}
