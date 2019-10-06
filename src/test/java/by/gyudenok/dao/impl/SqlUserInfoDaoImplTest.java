package by.gyudenok.dao.impl;

import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.Gender;
import by.gyudenok.entity.Role;
import by.gyudenok.entity.User;
import by.gyudenok.entity.UserInfo;
import org.junit.*;

import java.sql.SQLException;
import java.util.Calendar;

public class SqlUserInfoDaoImplTest {

    SqlDaoFactory factory = SqlDaoFactory.getInstance();
    SqlUserInfoDaoImpl mSqlUserInfoDao = factory.getSqlUserInfoDao();
    static UserInfo mUserInfo = null;
    static User mTestUser = null;

    @BeforeClass
    public static void init() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999,05,13, 0, 0, 0);
        mUserInfo = new UserInfo(
                "testLink",
                "testId",
                "testName",
                "testSurname",
                calendar,
                "testPhone",
                Gender.FEMALE
        );
       mTestUser = new User("testId",
               "testLogin",
               "testPassword",
               "testEmail",
               Role.ADMIN);
        try {
            SqlDaoFactory.getInstance().getSqlUserDao().create(mTestUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void destroy() throws SQLException {
        SqlDaoFactory.getInstance().getSqlUserDao().delete("testId");
    }

    @Test
    public void create() throws SQLException {
        boolean actual = mSqlUserInfoDao.create(mUserInfo);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read() throws SQLException {
        //call create cause delete called before update
        create();
        UserInfo actual = mSqlUserInfoDao.read("testId");
        UserInfo expected = mUserInfo;
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test
    public void update() throws SQLException {
        //call create cause delete called before update
        create();
        mUserInfo.setGender(Gender.OTHER);
        mUserInfo.setPhoneNumber("testUpdateNumber");
        int actual = mSqlUserInfoDao.update(mUserInfo);
        int expected = 1;
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test
    public void delete() throws SQLException {
        int actual = mSqlUserInfoDao.delete("testId");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }
}