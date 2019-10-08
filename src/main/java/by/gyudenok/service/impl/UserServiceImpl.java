package by.gyudenok.service.impl;

import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.dao.impl.SqlUserDaoImpl;
import by.gyudenok.dao.impl.SqlUserInfoDaoImpl;
import by.gyudenok.entity.User;
import by.gyudenok.entity.UserInfo;
import by.gyudenok.exception.DaoException;
import by.gyudenok.exception.ServiceException;
import by.gyudenok.exception.ValidatorException;
import by.gyudenok.service.UserService;
import by.gyudenok.service.validator.UserInfoValidator;
import by.gyudenok.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private static final SqlDaoFactory factory = SqlDaoFactory.getInstance();
    private static final SqlUserDaoImpl userDao = factory.getSqlUserDao();
    private static final SqlUserInfoDaoImpl userInfoDao = factory.getSqlUserInfoDao();
    private static UserValidator validator = new UserValidator();
    private static UserInfoValidator sUserInfoValidator = new UserInfoValidator();

    @Override
    public User signIn(String login, String password) throws ServiceException {
        User user = null;
        try {
            validator.validateUserLoginNPassword(login, password);
            user = userDao.readByLoginNPassword(login, password);
        }catch (ValidatorException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        try {
            validator.validateUser(user);
        } catch (ValidatorException e) {
            throw new ServiceException(e.getMessage());
        }
        return user;
    }

    @Override
    public User signUp(final User user, final UserInfo userInfo) throws ServiceException {
        User registredUser = null;

        try {
            validator.validateUserLoginNPassword(user.getLogin(), user.getPassword());
            validator.validateUser(user);
            sUserInfoValidator.validateUserInfo(userInfo);
        } catch (ValidatorException e) {
            throw new ServiceException(e.getMessage());
        }

        try {
            userDao.create(user);
            userInfoDao.create(userInfo);
        }catch (DaoException e) {
            throw new ServiceException("Some problem with db");
        }
        registredUser = user;
        registredUser.setUserInfo(userInfo);
        return user;
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public boolean deleteAccount(String id) throws ServiceException {
        try {
            validator.validateDelete(userDao.delete(id));
        }catch (ValidatorException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return true;
    }

    @Override
    public List<User> getListOfUsers() {
        return null;
    }

    @Override
    public boolean editProfile(User user, UserInfo userInfo) {
        return false;
    }
}
