package by.gyudenok.domain;

public class User {
    private UserInfo mUserInfo;
    private String mId;
    private String mLogin;
    private String mPassword;
    private String mEmail;
    private Role mRole;

    public User() {

    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Role getRole() {
        return mRole;
    }

    public void setRole(Role role) {
        mRole = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "mUserInfo=" + mUserInfo +
                ", mId='" + mId + '\'' +
                ", mLogin='" + mLogin + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mRole=" + mRole +
                '}';
    }
}
