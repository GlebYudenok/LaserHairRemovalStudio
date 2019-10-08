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

    @Test(expected = NullPointerException.class)
    public void createWithNullArgs() throws DaoException {
        mSqlPictureDao.create(null);
    }

    @Test(expected = DaoException.class)
    public void readWithNullArgs() throws DaoException {
        mSqlPictureDao.read(null);
    }

    @Test(expected = NullPointerException.class)
    public void updateWithNullArgs() throws DaoException {
        mSqlPictureDao.update(null);
    }

    @Test(expected = DaoException.class)
    public void deleteWithNullArgs() throws DaoException {
        mSqlPictureDao.delete(null);
    }

    @Test(expected = DaoException.class)
    public void createWithExsistingIdField() throws DaoException {
        create();
        try {
            mSqlPictureDao.create(new Picture("testId", "testLink"));
        }catch (DaoException e){
            throw new DaoException();
        }finally {
            mSqlPictureDao.delete("testId");
        }
    }

    @Test(expected = DaoException.class)
    public void readWithWrongArgs() throws DaoException {
        mSqlPictureDao.read("wrongId");
    }

    @Test(expected = DaoException.class)
    public void updateWithWrongArgs() throws DaoException {
        mSqlPictureDao.update(new Picture("wrongId", "link"));
    }

    @Test(expected = DaoException.class)
    public void deleteWithWrongArgs() throws DaoException {
        mSqlPictureDao.delete("wrongId");
    }
}