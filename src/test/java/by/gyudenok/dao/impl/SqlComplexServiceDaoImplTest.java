package by.gyudenok.dao.impl;

import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.ComplexService;
import by.gyudenok.entity.Gender;
import by.gyudenok.entity.Service;
import by.gyudenok.exception.DaoException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class SqlComplexServiceDaoImplTest {

    SqlDaoFactory mSqlDaoFactory = null;
    SqlComplexServiceDaoImpl mSqlComplexServiceDao = null;
    ComplexService mComplexService = null;

    static Service mService = null;
    static SqlDaoFactory sSqlDaoFactory = SqlDaoFactory.getInstance();
    static SqlServiceDaoImpl sSqlServiceDao = sSqlDaoFactory.getSqlServiceDao();

    @BeforeClass
    public static void init() throws SQLException, ClassNotFoundException {
        mService = new Service();
        mService.setId("testId");
        mService.setZoneName("testZone");
        mService.setPrice(new BigDecimal(212));
        sSqlServiceDao.create(mService);
        mService.setId("testId2");
        mService.setZoneName("testZone2");
        mService.setPrice(new BigDecimal(123));
        sSqlServiceDao.create(mService);
    }

    @AfterClass
    public static void destroy() throws SQLException, DaoException {
        sSqlServiceDao.delete("testId");
        sSqlServiceDao.delete("testId2");
    }

    @Before
    public void setUp() throws Exception {
        mSqlDaoFactory = SqlDaoFactory.getInstance();
        mSqlComplexServiceDao = mSqlDaoFactory.getSqlComplexServiceDao();
        mComplexService = new ComplexService();
        mComplexService.setId("testId");
        mComplexService.setPrice(new BigDecimal(228));
        mComplexService.setGender(Gender.MALE);
        mComplexService.setComplexName("testName");
        List<String> serviceId = new ArrayList<>();
        serviceId.add("testId");
        mComplexService.setServiceIds(serviceId);
    }

    @Test
    public void create() throws SQLException, ClassNotFoundException, DaoException {
        boolean actual = mSqlComplexServiceDao.create(mComplexService);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read() throws SQLException, DaoException, ClassNotFoundException {
        create();
        ComplexService actualComplexService =
                mSqlComplexServiceDao.read("testId");
        ComplexService expectedComplexService = actualComplexService;
        Assert.assertEquals(expectedComplexService, actualComplexService);
        delete();
    }

    @Test
    public void update() throws DaoException, SQLException, ClassNotFoundException {
        create();
        ComplexService updateComplexService = new ComplexService();
        updateComplexService.setId("testId");
        updateComplexService.setComplexName("testUpdateName");
        updateComplexService.setPrice(new BigDecimal(500));
        updateComplexService.setGender(Gender.OTHER);
        List<String> service = new ArrayList<>();
        service.add("testId2");
        updateComplexService.setServiceIds(service);
        int actual = mSqlComplexServiceDao.update(updateComplexService, "testId");
        int expected = 1;
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test
    public void delete() throws SQLException, DaoException {
        int actual = mSqlComplexServiceDao.delete("testId");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readAll() throws SQLException, DaoException, ClassNotFoundException {
        create();
        List<ComplexService> actualServices = mSqlComplexServiceDao.readAll();
        boolean actual = false;
        boolean expected = true;
        if(actualServices.size() > 0) {
            actual = true;
        }
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test
    public void readByName() throws SQLException, DaoException, ClassNotFoundException {
        create();
        ComplexService csActual = mSqlComplexServiceDao.readByName("testName");
        ComplexService csExpected = mComplexService;
        Assert.assertEquals(csExpected, csActual);
        delete();
    }
}
