package by.gyudenok.dao.impl;

import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.Gender;
import by.gyudenok.entity.Role;
import by.gyudenok.entity.User;
import by.gyudenok.entity.UserInfo;
import by.gyudenok.exception.DaoException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

public class SqlUserInfoDaoImplTest {

    SqlDaoFactory factory = SqlDaoFactory.getInstance();
    SqlUserInfoDaoImpl mSqlUserInfoDao = factory.getSqlUserInfoDao();
    static UserInfo mUserInfo = null;
    static User mTestUser = null;

    @BeforeClass
    public static void init() throws DaoException {
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
       SqlDaoFactory.getInstance().getSqlUserDao().create(mTestUser);
    }

    @AfterClass
    public static void destroy() throws DaoException {
        SqlDaoFactory.getInstance().getSqlUserDao().delete("testId");
    }

    @Test
    public void create() throws DaoException {
        boolean actual = mSqlUserInfoDao.create(mUserInfo);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read() throws DaoException {
        //call create cause delete called before update
        create();
        UserInfo actual = mSqlUserInfoDao.read("testId");
        UserInfo expected = mUserInfo;
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test
    public void update() throws DaoException {
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
    public void delete() throws DaoException {
        int actual = mSqlUserInfoDao.delete("testId");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readAll() throws DaoException {
        create();
        List<UserInfo> expectedUsersInfo = mSqlUserInfoDao.readAll();
        boolean expected = true;
        boolean actual = false;
        if(expectedUsersInfo.size() > 0) {
            actual = true;
        }
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test
    public void readByName() throws DaoException {
        create();
        UserInfo actualUserInfo = mSqlUserInfoDao.readByName(
                "testName", "testSurname");
        UserInfo expectedUserInfo = mUserInfo;
        Assert.assertEquals(expectedUserInfo, actualUserInfo);
        delete();
    }

    @Test(expected = NullPointerException.class)
    public void createWithNullArguments() throws DaoException {
        mSqlUserInfoDao.create(null);
    }

    @Test(expected = NullPointerException.class)
    public void readWithNullArguments() throws DaoException {
        mSqlUserInfoDao.read(null);
    }

    @Test(expected = NullPointerException.class)
    public void updateWithNullArguments() throws DaoException {
        mSqlUserInfoDao.update(null);
    }

    @Test(expected = DaoException.class)
    public void deleteWithNullArguments() throws DaoException {
        mSqlUserInfoDao.delete(null);
    }

    @Test(expected = NullPointerException.class)
    public void readByNameWithNullArguments() throws DaoException {
        mSqlUserInfoDao.readByName(null, null);
    }

    /**
     *
     * @throws DaoException
     */
    @Test(expected = NullPointerException.class)
    public void createWithWrongArguments() throws DaoException {
        mSqlUserInfoDao.create(null);
    }

    @Test(expected = NullPointerException.class)
    public void readWithWrongArguments() throws DaoException {
        mSqlUserInfoDao.read("wrongId");
    }

    @Test(expected = DaoException.class)
    public void updateWithWrongArguments() throws DaoException {
        mSqlUserInfoDao.update(new UserInfo("link", "wrongId",
                "name", "surname", Calendar.getInstance(),
                "num", Gender.FEMALE));
    }

    @Test(expected = DaoException.class)
    public void deleteWithWrongArguments() throws DaoException {
        mSqlUserInfoDao.delete("wrongId");
    }

    @Test(expected = NullPointerException.class)
    public void readByNameWithWrongArguments() throws DaoException {
        mSqlUserInfoDao.readByName("wrongName", "wrongName");
    }
}