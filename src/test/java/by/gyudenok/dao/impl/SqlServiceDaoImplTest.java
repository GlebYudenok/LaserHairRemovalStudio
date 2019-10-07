package by.gyudenok.dao.impl;

import by.gyudenok.dao.Dao;
import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.Service;
import by.gyudenok.exception.DaoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class SqlServiceDaoImplTest {

    SqlDaoFactory mSqlDaoFactory = null;
    SqlServiceDaoImpl mSqlServiceDao = null;
    Service mService = null;

    @Before
    public void setUp() {
        mSqlDaoFactory = SqlDaoFactory.getInstance();
        mSqlServiceDao = mSqlDaoFactory.getSqlServiceDao();
        mService = new Service();
        mService.setId("testId");
        mService.setZoneName("testZone");
        mService.setPrice(new BigDecimal(500));
    }

    @Test
    public void create() throws DaoException {
        boolean actual = mSqlServiceDao.create(mService);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read() throws DaoException {
        create();
        Service actualService = mSqlServiceDao.read("testId");
        Service expectedService = mService;
        Assert.assertEquals(expectedService, actualService);
        delete();
    }

    @Test
    public void update() throws DaoException {
        create();
        Service updateService = new Service();
        updateService.setPrice(new BigDecimal(228));
        updateService.setZoneName("testUpdateZone");
        updateService.setId("testId");
        int actualService = mSqlServiceDao.update(updateService);
        int expectedService = 1;
        Assert.assertEquals(expectedService, actualService);
        delete();
    }

    @Test
    public void delete() throws DaoException {
        int actual = mSqlServiceDao.delete("testId");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readAll() throws DaoException {
        create();
        List<Service> services = mSqlServiceDao.readAll();
        boolean actual = false;
        boolean expected = true;
        if(services.size() > 0) {
            actual = true;
        }
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test
    public void readByName() throws DaoException {
        create();
        Service actualService = mSqlServiceDao.readByName("testZone");
        Service expectedService = mService;
        Assert.assertEquals(expectedService, actualService);
        delete();
    }

    @Test(expected = NullPointerException.class)
    public void createWithNullArguments() throws DaoException {
        mSqlServiceDao.create(null);
    }

    @Test(expected = DaoException.class)
    public void readWithNullArguments() throws DaoException {
        mSqlServiceDao.read(null);
    }

    @Test(expected = NullPointerException.class)
    public void updateWithNullArguments() throws DaoException {
        mSqlServiceDao.update(null);
    }

    @Test(expected = DaoException.class)
    public void deleteWithNullArguments() throws DaoException {
        mSqlServiceDao.delete(null);
    }

    @Test(expected = DaoException.class)
    public void createWithExsistingId() throws DaoException {
        try {
            create();
            mSqlServiceDao.create(new Service("testId", "testName", new BigDecimal(123123)));
        }catch (DaoException e){
            throw new DaoException();
        }finally {
            delete();
        }
    }

    @Test(expected = DaoException.class)
    public void readNotExsistingService() throws DaoException {
        mSqlServiceDao.read("wrongId");
    }

    @Test(expected = DaoException.class)
    public void updateNotExsistingService() throws DaoException {
        mSqlServiceDao.update(new Service("wrongId", "name", new BigDecimal(123)));
    }

    @Test(expected = DaoException.class)
    public void deleteNotExsistingService() throws DaoException {
        mSqlServiceDao.delete("wrongID");
    }

    @Test(expected = DaoException.class)
    public void readByNameNotExsistingService() throws DaoException {
        mSqlServiceDao.readByName("wrongName");
    }

    @Test(expected = DaoException.class)
    public void readByNameNull() throws DaoException {
        mSqlServiceDao.readByName(null);
    }
}