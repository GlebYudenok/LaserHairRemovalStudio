package by.gyudenok.dao.impl;

import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.*;
import by.gyudenok.exception.DaoException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(JUnit4.class)
public class SqlAppointmentDaoImplTest {

    Appointment mAppointment = null;
    SqlDaoFactory mSqlDaoFactory = null;
    SqlAppointmentDaoImpl mSqlAppointmentDao = null;

    static User sUser = null;
    static Service sService = null;
    static ComplexService sComplexService = null;

    @BeforeClass
    public static void init() throws DaoException {
        sUser = new User(
                "testId", "testLogin",
                "testPassword", "testEmail",
                Role.ADMIN
        );

        sService = new Service();
        sService.setId("testId");
        sService.setPrice(new BigDecimal(234));
        sService.setZoneName("testZone");
        SqlDaoFactory.getInstance().getSqlServiceDao().create(sService);

        sComplexService = new ComplexService();
        List<String> service = new ArrayList<>();
        service.add(sService.getId());
        sComplexService.setServiceIds(service);
        sComplexService.setGender(Gender.OTHER);
        sComplexService.setPrice(new BigDecimal(213));
        sComplexService.setComplexName("testName");
        sComplexService.setId("testId");
        SqlDaoFactory.getInstance().getSqlComplexServiceDao().create(sComplexService);
        SqlDaoFactory.getInstance().getSqlUserDao().create(sUser);

        sService.setId("testId2");
        sService.setPrice(new BigDecimal(235));
        sService.setZoneName("testZone2");
        SqlDaoFactory.getInstance().getSqlServiceDao().create(sService);

        service.add(sService.getId());
        sComplexService.setServiceIds(service);
        sComplexService.setGender(Gender.MALE);
        sComplexService.setPrice(new BigDecimal(225));
        sComplexService.setComplexName("testName2");
        sComplexService.setId("testId2");
        SqlDaoFactory.getInstance().getSqlComplexServiceDao().create(sComplexService);
    }

    @AfterClass
    public static void destroy() throws DaoException {
        SqlDaoFactory.getInstance().getSqlUserDao().delete("testId");
        SqlDaoFactory.getInstance().getSqlComplexServiceDao().delete("testId");
        SqlDaoFactory.getInstance().getSqlServiceDao().delete("testId");
        SqlDaoFactory.getInstance().getSqlServiceDao().delete("testId2");
    }

    @Before
    public void setUp() {
        mSqlDaoFactory = SqlDaoFactory.getInstance();
        mSqlAppointmentDao = mSqlDaoFactory.getSqlAppointmentDao();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 10, 15, 16,45, 0);
        mAppointment = new Appointment();
        mAppointment.setUserId("testId");
        mAppointment.setsDateOfMeet(calendar);
        mAppointment.setComplexId("testId");
        mAppointment.setId("testId");
        mAppointment.setServiceId("testId");
    }

    @Test
    public void create() throws DaoException {
        boolean actual = mSqlAppointmentDao.create(mAppointment);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read() throws DaoException {
        create();
        Appointment actualAppointment = mSqlAppointmentDao.read("testId");
        Appointment expectedAppointment = mAppointment;
        Assert.assertEquals(expectedAppointment, actualAppointment);
        delete();
    }

    @Test
    public void update() throws DaoException {
        create();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.SEPTEMBER, 11, 2,28,0);
        Appointment appointment = new Appointment();
        appointment.setId("testId");
        appointment.setUserId("testId");
        appointment.setServiceId("testId2");
        appointment.setComplexId("testId2");
        appointment.setsDateOfMeet(calendar);
        int actual = mSqlAppointmentDao.update(appointment);
        int expected = 1;
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test
    public void delete() throws DaoException {
        int actual = mSqlAppointmentDao.delete("testId");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readAll() throws DaoException {
        create();
        List<Appointment> actualAppointments = mSqlAppointmentDao.readAll();
        boolean actual = false;
        boolean expected = true;
        if(actualAppointments.size() > 0) {
            actual = true;
        }
        Assert.assertEquals(expected, actual);
        delete();
    }

    @Test(expected = DaoException.class)
    public void createWithWrongArgs() throws DaoException {
        Appointment wrongAppointment = new Appointment();
        wrongAppointment.setServiceId("wrongService");
        wrongAppointment.setComplexId("wrongComplex");
        wrongAppointment.setUserId("wrongUser");
        Calendar calendar = Calendar.getInstance();
        wrongAppointment.setsDateOfMeet(calendar);
        wrongAppointment.setId("testId");
        mSqlAppointmentDao.create(wrongAppointment);
    }

    @Test(expected = DaoException.class)
    public void readWithWrongArgs() throws DaoException {
        mSqlAppointmentDao.read("wrongId");
    }

    @Test(expected = DaoException.class)
    public void updateWithWrongArgs() throws DaoException {
        Appointment wrongAppointment = new Appointment();
        wrongAppointment.setServiceId("wrongService");
        wrongAppointment.setComplexId("wrongComplex");
        wrongAppointment.setUserId("wrongUser");
        Calendar calendar = Calendar.getInstance();
        wrongAppointment.setsDateOfMeet(calendar);
        wrongAppointment.setId("WrongTestId");
        mSqlAppointmentDao.update(wrongAppointment);
    }

    @Test(expected = DaoException.class)
    public void deleteWithWrongArgs() throws DaoException {
        mSqlAppointmentDao.delete("wrongId");
    }
}