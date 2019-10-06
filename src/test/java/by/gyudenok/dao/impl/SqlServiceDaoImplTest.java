package by.gyudenok.dao.impl;

import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.Service;
import by.gyudenok.exception.DaoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class SqlServiceDaoImplTest {

    SqlDaoFactory mSqlDaoFactory = null;
    SqlServiceDaoImpl mSqlServiceDao = null;
    Service mService = null;

    @Before
    public void setUp() throws Exception {
        mSqlDaoFactory = SqlDaoFactory.getInstance();
        mSqlServiceDao = mSqlDaoFactory.getSqlServiceDao();
        mService = new Service();
        mService.setId("testId");
        mService.setZoneName("testZone");
        mService.setPrice(new BigDecimal(500));
    }

    @Test
    public void create() throws SQLException, ClassNotFoundException, DaoException {
        boolean actual = mSqlServiceDao.create(mService);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read() throws SQLException, DaoException, ClassNotFoundException {
        create();
        Service actualService = mSqlServiceDao.read("testId");
        Service expectedService = mService;
        Assert.assertEquals(expectedService, actualService);
        delete();
    }

    @Test
    public void update() throws DaoException, SQLException, ClassNotFoundException {
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
    public void delete() throws SQLException, DaoException, ClassNotFoundException {
        int actual = mSqlServiceDao.delete("testId");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readAll() throws DaoException, SQLException, ClassNotFoundException {
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
    public void readByName() throws DaoException, SQLException, ClassNotFoundException {
        create();
        Service actualService = mSqlServiceDao.readByName("testZone");
        Service expectedService = mService;
        Assert.assertEquals(expectedService, actualService);
        delete();
    }
}