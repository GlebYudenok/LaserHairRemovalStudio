package by.gyudenok.dao.factory;

import by.gyudenok.dao.impl.*;

public class SqlDaoFactory {

    private static final SqlDaoFactory INSTANCE =
            new SqlDaoFactory();

    private SqlDaoFactory() {

    }

    private final SqlAppointmentDaoImpl mSqlAppointmentDao =
            new SqlAppointmentDaoImpl();
    private final SqlComplexServiceDaoImpl mSqlComplexServiceDao =
            new SqlComplexServiceDaoImpl();
    private final SqlPictureDaoImpl mSqlPictureDao =
            new SqlPictureDaoImpl();
    private final SqlServiceDaoImpl mSqlServiceDao =
            new SqlServiceDaoImpl();
    private final SqlUserDaoImpl mSqlUserDao =
            new SqlUserDaoImpl();
    private final SqlUserInfoDaoImpl mSqlUserInfoDao =
            new SqlUserInfoDaoImpl();

    public static SqlDaoFactory getInstance() {
        return INSTANCE;
    }

    public SqlAppointmentDaoImpl getSqlAppointmentDao() {
        return mSqlAppointmentDao;
    }

    public SqlComplexServiceDaoImpl getSqlComplexServiceDao() {
        return mSqlComplexServiceDao;
    }

    public SqlPictureDaoImpl getSqlPictureDao() {
        return mSqlPictureDao;
    }

    public SqlServiceDaoImpl getSqlServiceDao() {
        return mSqlServiceDao;
    }

    public SqlUserDaoImpl getSqlUserDao() {
        return mSqlUserDao;
    }

    public SqlUserInfoDaoImpl getSqlUserInfoDao() {
        return mSqlUserInfoDao;
    }}

