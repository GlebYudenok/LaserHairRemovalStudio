package by.gyudenok.dao.impl;

import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.Dao;
import by.gyudenok.entity.Gender;
import by.gyudenok.entity.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class SqlUserInfoDaoImpl implements Dao<UserInfo> {

    private static final Logger LOGGER = LogManager.getLogger(SqlUserInfoDaoImpl.class);
    private static final String SQL_READ_BY_ID_QUERY = new String("SELECT *FROM USER_INFO WHERE USER_ID = ?");
    private static final String SQL_DELETE_BY_ID_QUERY = new String("DELETE FROM USER_INFO WHERE USER_ID = ?");
    private static final String SQL_UPDATE_BY_ID_QUERY = new String("UPDATE USER_INFO SET name=?," +
            "surname=?, avatar_link=?, birth_date=?, phone_number=?, gender=? WHERE user_id=?");

    @Override
    public void create() {

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
                    resultSet.getString("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("avatar_link"),
                    calendar,
                    resultSet.getString("phone_number"),
                    Gender.valueOf(resultSet.getString("gender").toUpperCase())
            );
        }
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
        ps.setString(7, userInfo.getId());
        int code = ps.executeUpdate();
        if(code > 0) {
            LOGGER.info("User info was update successfully!");
        }else {
            LOGGER.warn("User info not found or can't update!");
        }
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
        return code;
    }
}
