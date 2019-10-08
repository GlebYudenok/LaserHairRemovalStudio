package by.gyudenok.service.validator;

import by.gyudenok.entity.Gender;
import by.gyudenok.entity.UserInfo;
import by.gyudenok.exception.ValidatorException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;

public class UserInfoValidatorTest {

    private static UserInfoValidator mUserInfoValidator = null;

    @BeforeClass
    public static void init() {
        mUserInfoValidator = new UserInfoValidator();
    }

    @Test
    public void validateUserInfo() throws ValidatorException {
        boolean actual = mUserInfoValidator.validateUserInfo(new UserInfo(
                "testAvatar", "testId", "testName",
                "testSurname", Calendar.getInstance(),
                "+375297125115", Gender.FEMALE)
        );
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ValidatorException.class)
    public void validateWrongPhoneUserInfo() throws ValidatorException {
        mUserInfoValidator.validateUserInfo(new UserInfo(
                "testAvatar", "testId", "testName",
                "testSurname", Calendar.getInstance(),
                "wrongphone", Gender.FEMALE)
        );
    }
}