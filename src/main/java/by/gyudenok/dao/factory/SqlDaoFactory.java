package by.gyudenok.dao.factory;

import by.gyudenok.dao.impl.*;

public class SqlDaoFactory {

    private static final SqlDaoFactory instance =
            new SqlDaoFactory();

    private final SqlAppointmentDao mSqlAppointmentDao =
            new SqlAppointmentDao();
    private final SqlComplexServiceDao mSqlComplexServiceDao =
            new SqlComplexServiceDao();
    private final SqlPictureDao mSqlPictureDao =
            new SqlPictureDao();
    private final SqlServiceDao mSqlServiceDao =
            new SqlServiceDao();
    private final SqlUserDao mSqlUserDao =
            new SqlUserDao();
    private final SqlUserInfoDao mSqlUserInfoDao =
            new SqlUserInfoDao();

    public static SqlDaoFactory getInstance() {
        return instance;
    }

    public SqlAppointmentDao getSqlAppointmentDao() {
        return mSqlAppointmentDao;
    }

    public SqlComplexServiceDao getSqlComplexServiceDao() {
        return mSqlComplexServiceDao;
    }

    public SqlPictureDao getSqlPictureDao() {
        return mSqlPictureDao;
    }

    public SqlServiceDao getSqlServiceDao() {
        return mSqlServiceDao;
    }

    public SqlUserDao getSqlUserDao() {
        return mSqlUserDao;
    }

    public SqlUserInfoDao getSqlUserInfoDao() {
        return mSqlUserInfoDao;
    }
}
