package by.gyudenok.dao.impl;

import by.gyudenok.dao.ConnectionPool;
import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.Role;
import by.gyudenok.entity.User;
import org.junit.*;

import java.sql.SQLException;
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
    public void create() throws SQLException {
        boolean actual = mSqlUserDao.create(mUser);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read() throws SQLException {
        create();
        User actualUser = mSqlUserDao.read("test");
        User expectedUser = mUser;
        Assert.assertEquals(expectedUser, actualUser);
        delete();
    }

    @Test
    public void update() throws SQLException {
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
    public void readByLogin() throws SQLException {
        create();
        User actualUser = mSqlUserDao.readByLogin("testLogin");
        User expectedUser = mUser;
        Assert.assertEquals(expectedUser, actualUser);
        delete();
    }

    @Test
    public void readAll() throws SQLException {
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
    public void delete() throws SQLException {
        int actual = mSqlUserDao.delete("test");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }
}