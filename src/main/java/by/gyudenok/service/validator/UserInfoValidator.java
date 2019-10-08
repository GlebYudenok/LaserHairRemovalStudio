package by.gyudenok.service.validator;

import by.gyudenok.entity.UserInfo;
import by.gyudenok.exception.ValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfoValidator {

    private Pattern pattern;
    private Matcher matcher;
    private static final String PHONE_PATTERN = "[+]\\d{9,15}";

    public boolean validateUserInfo(UserInfo userInfo) throws ValidatorException {

        if(!validatePhoneNumber(userInfo.getPhoneNumber())) {
            throw new ValidatorException("Wrong phoneNumber");
        }

        if(userInfo == null) {
            throw new ValidatorException("User info is null");
        }
        if(userInfo.getPhoneNumber() == null) {
            throw new ValidatorException("No phone number");
        }
        if(userInfo.getUserId() == null) {
            throw new ValidatorException("Id is null");
        }
        if(userInfo.getName() == null) {
            throw new ValidatorException("Name is null");
        }
        return true;
    }

    private boolean validatePhoneNumber(String phone) {
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(phone);
        if(matcher.matches()) {
            return true;
        }
        return false;
    }
}
