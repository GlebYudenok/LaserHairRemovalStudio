package by.gyudenok.dao.impl;

import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.Picture;
import by.gyudenok.exception.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class SqlPictureDaoImplTest {

    SqlDaoFactory mSqlDaoFactory = null;
    SqlPictureDaoImpl mSqlPictureDao = null;
    Picture mPicture = null;

    @Before
    public void setUp() throws Exception {
        mSqlDaoFactory = SqlDaoFactory.getInstance();
        mSqlPictureDao = mSqlDaoFactory.getSqlPictureDao();
        mPicture = new Picture();
        mPicture.setLink("testLink");
        mPicture.setId("testId");
    }

    @Test
    public void create() throws SQLException, ClassNotFoundException, DaoException {
        boolean actual = mSqlPictureDao.create(mPicture);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read() throws DaoException, SQLException, ClassNotFoundException {
        create();
        Picture actualService = mSqlPictureDao.read("testId");
        Picture expectedService = mPicture;
        Assert.assertEquals(expectedService, actualService);
        delete();
    }

    @Test
    public void update() throws DaoException, SQLException, ClassNotFoundException {
        create();
        Picture updatePicture = new Picture();
        updatePicture.setId("testId");
        updatePicture.setLink("updateTestLink");
        int actual = mSqlPictureDao.update(updatePicture);
        int expected = 1;
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test
    public void delete() throws SQLException, DaoException {
        int actual = mSqlPictureDao.delete("testId");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }
}