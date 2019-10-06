package by.gyudenok.entity;

public class User {
    private UserInfo mUserInfo;
    private String mId;
    private String mLogin;
    private String mPassword;
    private String mEmail;
    private Role mRole;

    public User(String id, String login, String password, String email, Role role) {
        mId = id;
        mLogin = login;
        mPassword = password;
        mEmail = email;
        mRole = role;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (mUserInfo != null ? !mUserInfo.equals(user.mUserInfo) : user.mUserInfo != null) return false;
        if (mId != null ? !mId.equals(user.mId) : user.mId != null) return false;
        if (mLogin != null ? !mLogin.equals(user.mLogin) : user.mLogin != null) return false;
        if (mPassword != null ? !mPassword.equals(user.mPassword) : user.mPassword != null) return false;
        if (mEmail != null ? !mEmail.equals(user.mEmail) : user.mEmail != null) return false;
        return mRole == user.mRole;

    }

    @Override
    public int hashCode() {
        int result = mUserInfo != null ? mUserInfo.hashCode() : 0;
        result = 31 * result + (mId != null ? mId.hashCode() : 0);
        result = 31 * result + (mLogin != null ? mLogin.hashCode() : 0);
        result = 31 * result + (mPassword != null ? mPassword.hashCode() : 0);
        result = 31 * result + (mEmail != null ? mEmail.hashCode() : 0);
        result = 31 * result + (mRole != null ? mRole.hashCode() : 0);
        return result;
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
