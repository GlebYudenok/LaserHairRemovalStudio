package by.gyudenok.dao.impl;

import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.UserInfoDao;
import by.gyudenok.entity.Gender;
import by.gyudenok.entity.UserInfo;
import by.gyudenok.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SqlUserInfoDaoImpl implements UserInfoDao<UserInfo> {

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
    public boolean create(UserInfo userInfo) throws DaoException {
        int code = 0;
        try {
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

            code = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DaoException();
        }
        if(code > 0) {
            LOGGER.info("User info was inserted successfully!");
            return true;
        }else {
            LOGGER.warn("Cannot insert data");
            throw new DaoException();
        }
    }

    @Override
    public UserInfo read(String userId) throws DaoException {
        UserInfo userInfo = null;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_READ_BY_ID_QUERY);
            ps.setString(1, userId);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
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
        }catch (SQLException e) {
            throw new DaoException();
        }
        if(userInfo.getUserId() == null) {
            throw new NullPointerException();
        }
        return userInfo;
    }

    @Override
    public int update(UserInfo userInfo) throws DaoException {
        int code = 0;
        try {
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
            code = ps.executeUpdate();
            if (code > 0) {
                LOGGER.info("User info was update successfully!");
            } else {
                LOGGER.warn("User info not found or can't update!");
                throw new DaoException();
            }
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        return code;
    }

    @Override
    public int delete(String userId) throws DaoException {
        int code = 0;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_DELETE_BY_ID_QUERY);
            ps.setString(1, userId);
            code = ps.executeUpdate();
            if (code > 0) {
                LOGGER.info("User info was delete successfully!");
            } else {
                LOGGER.warn("User info not found or can't delete!");
                throw new DaoException();
            }
            ps.close();
        }catch (SQLException e) {
            throw new DaoException();
        }
        return code;
    }

    @Override
    public List<UserInfo> readAll() throws DaoException {
        List<UserInfo> userInfos = null;
        try {
            Statement statement = ConnectionPool.getInstance()
                    .getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_READ_ALL_QUERY);
            userInfos = new ArrayList<>();
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
        }catch (SQLException e) {
            throw new DaoException();
        }
        return userInfos;
    }

    @Override
    public UserInfo readByName(String name, String surname) throws DaoException {
        UserInfo userInfo = null;
        try {
            PreparedStatement ps = ConnectionPool.getInstance()
                    .getConnection().prepareStatement(SQL_READ_BY_NAME_QUERY);
            ps.setString(1, name);
            ps.setString(2, surname);
            ResultSet resultSet = ps.executeQuery();
            userInfo = new UserInfo();
            if (resultSet.next()) {
                Calendar calendar = Calendar.getInstance();
                Timestamp timestamp = resultSet.getTimestamp("birth_date");
                calendar.setTime(timestamp);
                userInfo.setUserId(resultSet.getString("user_id"));
                userInfo.setName(resultSet.getString("name"));
                userInfo.setSurname(resultSet.getString("surname"));
                userInfo.setAvatarLink(resultSet.getString("avatar_link"));
                userInfo.setPhoneNumber(resultSet.getString("phone_number"));
                userInfo.setGender(Gender.valueOf(resultSet.getString("gender").toUpperCase()));
                userInfo.setDateOfBirth(calendar);
            }
        }catch (SQLException e) {
            throw new DaoException();
        }
        if(userInfo.getUserId() == null) {
            throw new NullPointerException();
        }
        return userInfo;
    }
}
