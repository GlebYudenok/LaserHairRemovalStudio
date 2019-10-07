package by.gyudenok.dao.impl;

import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.Picture;
import by.gyudenok.exception.DaoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SqlPictureDaoImplTest {

    SqlDaoFactory mSqlDaoFactory = null;
    SqlPictureDaoImpl mSqlPictureDao = null;
    Picture mPicture = null;

    @Before
    public void setUp() throws DaoException {
        mSqlDaoFactory = SqlDaoFactory.getInstance();
        mSqlPictureDao = mSqlDaoFactory.getSqlPictureDao();
        mPicture = new Picture();
        mPicture.setLink("testLink");
        mPicture.setId("testId");
    }

    @Test
    public void create() throws DaoException {
        boolean actual = mSqlPictureDao.create(mPicture);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read() throws DaoException {
        create();
        Picture actualService = mSqlPictureDao.read("testId");
        Picture expectedService = mPicture;
        Assert.assertEquals(expectedService, actualService);
        delete();
    }

    @Test
    public void update() throws DaoException {
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
    public void delete() throws DaoException {
        int actual = mSqlPictureDao.delete("testId");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readAll() throws DaoException {
        create();
        List<Picture> pictureList = mSqlPictureDao.readAll();
        boolean actual = false;
        boolean expected = true;
        if(pictureList.size() > 0) {
            actual = true;
        }
        Assert.assertEquals(expected, actual);
        delete();
    }
}