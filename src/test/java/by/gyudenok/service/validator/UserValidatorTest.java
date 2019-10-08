package by.gyudenok.service.validator;

import by.gyudenok.entity.Role;
import by.gyudenok.entity.User;
import by.gyudenok.exception.ValidatorException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserValidatorTest {

    private static UserValidator mUserValidator = null;

    @BeforeClass
    public static void init() {
        mUserValidator = new UserValidator();
    }

    @Test
    public void validateUserLoginNPassword() throws ValidatorException {
        boolean actual = mUserValidator.validateUserLoginNPassword("someLogin","somePass");
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ValidatorException.class)
    public void validateWrongUserLoginNPassword() throws ValidatorException {
        mUserValidator.validateUserLoginNPassword(null, null);
    }

    @Test
    public void validateUser() throws ValidatorException {
        boolean actual = mUserValidator.validateUser(new User(
                "testId", "userName123",
                "some#Password123", "kek@gmail.ru", Role.ADMIN)
        );
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ValidatorException.class)
    public void validateWrongLoginUser() throws ValidatorException {
        mUserValidator.validateUser(new User(
                "testId", "us",
                "some#Password123", "kek@gmail.ru", Role.ADMIN)
        );
    }

    @Test(expected = ValidatorException.class)
    public void validateWrongPasswordUser() throws ValidatorException {
        mUserValidator.validateUser(new User(
                "testId", "userName123",
                "somepass", "kek@gmail.ru", Role.ADMIN)
        );
    }

    @Test(expected = ValidatorException.class)
    public void validateWrongEmailUser() throws ValidatorException {
        mUserValidator.validateUser(new User(
                "testId", "userName123",
                "somepass", "kekgmail.ru", Role.ADMIN)
        );
    }

    @Test
    public void validateDelete() throws ValidatorException {
        boolean actual = mUserValidator.validateDelete(1);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
}