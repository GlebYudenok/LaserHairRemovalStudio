package by.gyudenok.service.validator;

import by.gyudenok.entity.Role;
import by.gyudenok.entity.User;
import by.gyudenok.exception.ValidatorException;
import by.gyudenok.service.factory.ServiceFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.validation.Validator;

import static org.junit.Assert.*;

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

    @Test
    public void validateDelete() throws ValidatorException {
        boolean actual = mUserValidator.validateDelete(1);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void loginValidator() {
        boolean actual = mUserValidator.loginValidator("username");
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void passwordValidator() {
        boolean actual = mUserValidator.passwordValidator("Some@Pass123");
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void emailValidator() {
        boolean actual = mUserValidator.emailValidator("Vasya99@mail.ru");
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
}