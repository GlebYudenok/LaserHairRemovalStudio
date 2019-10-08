package by.gyudenok.dao.impl;

import by.gyudenok.dao.Dao;
import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.Role;
import by.gyudenok.entity.User;
import by.gyudenok.exception.DaoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SqlUserDaoImplTest {

    User mUser = null;
    SqlDaoFactory mSqlDaoFactory = null;
    SqlUserDaoImpl mSqlUserDao = null;

    @Before
    public void setUp() {
        mUser = new User("test",
                "testLogin",
                "testPassword",
                "testEmail",
                Role.ADMIN);
        mSqlDaoFactory = SqlDaoFactory.getInstance();
        mSqlUserDao = mSqlDaoFactory.getSqlUserDao();
    }

    @Test
    public void create() throws DaoException {
        boolean actual = mSqlUserDao.create(mUser);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read() throws DaoException {
        create();
        User actualUser = mSqlUserDao.read("test");
        User expectedUser = mUser;
        Assert.assertEquals(expectedUser, actualUser);
        delete();
    }

    @Test
    public void update() throws DaoException {
        create();
        User updateUser = new User(
                "test",
                "newlogin",
                "newPasswird",
                "newEmail",
                Role.ADMIN
        );
        int actual = mSqlUserDao.update(updateUser);
        int expected = 1;
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test
    public void readByLogin() throws DaoException {
        create();
        User actualUser = mSqlUserDao.readByLogin("testLogin");
        User expectedUser = mUser;
        Assert.assertEquals(expectedUser, actualUser);
        delete();
    }

    @Test
    public void readAll() throws DaoException {
        create();
        List<User> users = mSqlUserDao.readAll();
        boolean actual = false;
        boolean expected = true;
        if (users.size() > 0) {
            actual = true;
        }
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test
    public void delete() throws DaoException {
        int actual = mSqlUserDao.delete("test");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void createWithNullArguments() throws DaoException {
        mSqlUserDao.create(null);
    }

    @Test(expected = NullPointerException.class)
    public void readWithNullArguments() throws DaoException {
        mSqlUserDao.read(null);
    }

    @Test(expected = NullPointerException.class)
    public void updateWithNullArguments() throws DaoException {
        mSqlUserDao.update(null);
    }

    @Test(expected = DaoException.class)
    public void deleteWithNullArguments() throws DaoException {
        mSqlUserDao.delete(null);
    }

    @Test(expected = NullPointerException.class)
    public void readByLoginWithNullArguments() throws DaoException {
        mSqlUserDao.readByLogin(null);
    }

    @Test(expected = NullPointerException.class)
    public void readNotExsistingUser() throws DaoException {
        mSqlUserDao.read("wrongId");
    }

    @Test(expected = DaoException.class)
    public void updateNotExsistingUser() throws DaoException {
        mSqlUserDao.update(new User("wrongId", "testLogin",
                "testPassword", "testEmail", Role.ADMIN));
    }

    @Test(expected = DaoException.class)
    public void deleteNotExsistingUser() throws DaoException {
        mSqlUserDao.delete("wrongID");
    }

    @Test(expected = NullPointerException.class)
    public void readByLoginNotExsistingUser() throws DaoException {
        mSqlUserDao.readByLogin("wrongLogin");
    }

    @Test
    public void readByLoginNPassword() throws DaoException {
        create();
        User user = mSqlUserDao.readByLoginNPassword("testLogin", "testPassword");
        Assert.assertEquals(mUser, user);
        delete();
    }

    @Test(expected = NullPointerException.class)
    public void readByLoginNPasswordWithNullArguments() throws DaoException {
        mSqlUserDao.readByLoginNPassword(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void readByLoginNPasswordWithWrongArguments() throws DaoException {
        mSqlUserDao.readByLoginNPassword("wrongLogin", "wrongPassword");
    }
}