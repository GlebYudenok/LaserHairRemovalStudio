package by.gyudenok.service;

import by.gyudenok.entity.UserInfo;
import by.gyudenok.exception.DaoException;
import by.gyudenok.exception.ServiceException;

import java.util.List;

public interface UserService<User> {
    User signIn(String login, String password) throws ServiceException;
    User signUp(User user, UserInfo userInfo) throws ServiceException;
    User findUserByLogin(String login);
    User findById(String id);
    boolean deleteAccount(String id) throws ServiceException;
    List<User> getListOfUsers();
    boolean editProfile(User user, UserInfo userInfo);
}
