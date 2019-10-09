package by.gyudenok.dao.factory;

import by.gyudenok.dao.*;
import by.gyudenok.dao.impl.*;

public class SqlDaoFactory {

    private static final SqlDaoFactory INSTANCE =
            new SqlDaoFactory();

    private SqlDaoFactory() {

    }

    private final AppointmentDao mSqlAppointmentDao =
            new SqlAppointmentDaoImpl();
    private final ComplexServiceDao mSqlComplexServiceDao =
            new SqlComplexServiceDaoImpl();
    private final PictureDao mSqlPictureDao =
            new SqlPictureDaoImpl();
    private final ServiceDao mSqlServiceDao =
            new SqlServiceDaoImpl();
    private final UserDao mSqlUserDao =
            new SqlUserDaoImpl();
    private final UserInfoDao mSqlUserInfoDao =
            new SqlUserInfoDaoImpl();

    public static SqlDaoFactory getInstance() {
        return INSTANCE;
    }

    public AppointmentDao getSqlAppointmentDao() {
        return mSqlAppointmentDao;
    }

    public ComplexServiceDao getSqlComplexServiceDao() {
        return mSqlComplexServiceDao;
    }

    public PictureDao getSqlPictureDao() {
        return mSqlPictureDao;
    }

    public ServiceDao getSqlServiceDao() {
        return mSqlServiceDao;
    }

    public UserDao getSqlUserDao() {
        return mSqlUserDao;
    }

    public UserInfoDao getSqlUserInfoDao() {
        return mSqlUserInfoDao;
    }}

