package by.gyudenok.service.impl;

import by.gyudenok.dao.factory.SqlDaoFactory;
import by.gyudenok.entity.Gender;
import by.gyudenok.entity.Role;
import by.gyudenok.entity.User;
import by.gyudenok.entity.UserInfo;
import by.gyudenok.exception.DaoException;
import by.gyudenok.exception.ServiceException;
import by.gyudenok.service.UserService;
import by.gyudenok.service.factory.ServiceFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;

public class UserServiceImplTest {

    private static final ServiceFactory factory = ServiceFactory.getInstance();
    private static final UserService<User> service = factory.getsUserService();
    private static User mUser;
    private static UserInfo mUserInfo;
    private static User nullUser = null;
    private static UserInfo nullUserInfo = null;

    @BeforeClass
    public static void init() throws DaoException {
        mUser = new User("testId", "testLogin", "testP@ssword123",
                "testEmail@mail.ru", Role.ADMIN);
        mUserInfo = new UserInfo("testAvatar", "testId",
                "testName", "testSurname", Calendar.getInstance(),
                "+375445540091", Gender.FEMALE);
        mUser.setUserInfo(mUserInfo);
        SqlDaoFactory.getInstance().getSqlUserDao().create(mUser);
        SqlDaoFactory.getInstance().getSqlUserInfoDao().create(mUserInfo);
    }

    @Test
    public void signIn() throws ServiceException {
        String login = new String("testLogin");
        String password = new String("testP@ssword123");
        User actualUser = service.signIn(login, password);
        actualUser.setUserInfo(mUserInfo);
        User expectedUser = mUser;
        Assert.assertEquals(expectedUser, actualUser);
        mdeleteUserAccount();
    }

    @Test
    public void signUp() throws ServiceException {
        User actualUser = service.signUp(mUser, mUserInfo);
        User expectedUser = mUser;
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void mdeleteUserAccount() throws ServiceException {
        boolean actualValue = service.deleteAccount("testId");
        boolean expectedValue = true;
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findById() throws ServiceException {
        User actual = service.findById("testId");
        User expected = mUser;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getListOfUsers() throws ServiceException {
        int size = service.getListOfUsers().size();
        boolean actual = false;
        if(size > 0) {
            actual = true;
        }
        Assert.assertTrue(actual);
    }

    @Test
    public void editUser() throws ServiceException {
        User newUser = new User("testId", "testLogin", "testP@ssword890",
                "testMail@gmail.com", Role.ADMIN);
        UserInfo newUserInfo = new UserInfo("newAvatar",
                "testId", "testName", "testSurname", Calendar.getInstance(),
                "+375336688241", Gender.OTHER);
        boolean actual = service.editProfile(newUser, newUserInfo);
        Assert.assertTrue(actual);
    }
}