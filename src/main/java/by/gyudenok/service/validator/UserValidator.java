package by.gyudenok.service.validator;

import by.gyudenok.entity.User;
import by.gyudenok.entity.UserInfo;
import by.gyudenok.exception.ValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String USERNAME_PATTERN = "^[a-z A-Z0-9_-]{3,15}$";
    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public boolean validateUserLoginNPassword(String login, String password) throws ValidatorException {
        if(login == null || password == null) {
            throw new ValidatorException("Login or password is null");
        }
        return true;
    }

    public boolean validateUser(User user) throws ValidatorException {

        if(!loginValidator(user.getLogin())) {
            throw new ValidatorException("Wrong login");
        }

        if(!passwordValidator(user.getPassword())) {
            throw new ValidatorException("Wrong password");
        }

        if(!emailValidator(user.getEmail())) {
            throw new ValidatorException("Wrong email");
        }

        if(user == null) {
            throw new ValidatorException("User is null");
        }
        if(user.getId() == null
                || user.getLogin() == null
                || user.getPassword() == null) {
            throw new ValidatorException("id or login or password is null");
        }
        return true;
    }

    /**
     *login validation
     *             ^         # Start of the line
     *             [a-z0-9_-]# Match characters and symbols in the list, a-z, 0-9, underscore, hyphen
     *             {3,15}    # Length at least 3 characters and maximum length of 15
     *             $         # End of the line
     *
     * Login validator:
     * 1. min 3 characters
     * 2. “@” character is not allow
     * 3. too long, max characters of 15
     * valid login:
     *  mkyong34
     *  mkyong_2002
     *  mkyong-2002
     *  mk3-4_yong
     * @param username
     */
    public boolean loginValidator(String username) {
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        if(matcher.matches()) {
            return true;
        }
        return false;
    }


     /**
     *password validation
     *        (			    # Start of group
     *        (?=.*\d)		#   must contains one digit from 0-9
     *        (?=.*[a-z])		#   must contains one lowercase characters
     *        (?=.*[A-Z])		#   must contains one uppercase characters
     *        (?=.*[@#$%])    #   must contains one special symbols in the list "@#$%"
     *        .		        #   match anything with previous condition checking
     *        {6,20}   	    #   length at least 6 characters and maximum of 20
     *        )               # end of group
     *
     *
     * Password validator:
     *      minimum 6 characters
     *      uppercase characters is required
     *      special symbol “*” is not allow here
     *      digit is required
     *      lower case character is required
     **/
    public boolean passwordValidator(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        if(matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * email validator:
     *      ^	        		#start of the line
     *      [_A-Za-z0-9-\\+]+	#must start with string in the bracket [ ], must contains one or more (+)
     *              (			#start of group #1
     *     \\.[_A-Za-z0-9-]+	#follow by a dot "." and string in the bracket [ ], must contains one or more (+)
     *               )*			#end of group #1, this group is optional (*)
     *              @			#must contains a "@" symbol
     *       [A-Za-z0-9-]+      #follow by string in the bracket [ ], must contains one or more (+)
     *              (			#start of group #2 - first level TLD checking
     *         \\.[A-Za-z0-9]+  #follow by a dot "." and string in the bracket [ ], must contains one or more (+)
     *                  )*		#end of group #2, this group is optional (*)
     *              (			#start of group #3 - second level TLD checking
     *         \\.[A-Za-z]{2,}  #follow by a dot "." and string in the bracket [ ], with minimum length of 2
     *              )			#end of group #3
     *              $			#end of the line
     *
     *  must contains “@” symbol
     *  tld can not start with dot “.”
     *  “.a” is not a valid tld, last tld must contains at least two characters
     *  tld can not start with dot “.”
     *  tld can not start with dot “.”
     *  email’s first character can not start with dot “.”
     *  email’s is only allow character, digit, underscore and dash
     *  email’s tld is only allow character and digit
     *  double dots “.” are not allow
     *  email’s last character can not end with dot “.”
     *  double “@” is not allow
     *  email’s tld which has two characters can not contains digit
     */
    public boolean emailValidator(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        if(matcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean validateDelete(int code) throws ValidatorException {
        if(code <= 0) {
            throw new ValidatorException("Cannot delete user");
        }
        return true;
    }
}
