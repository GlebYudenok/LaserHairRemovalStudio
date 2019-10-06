package by.gyudenok.entity;

import java.util.Calendar;

public class UserInfo {
    private String mAvatarLink;
    private String mUserId;
    private String mName;
    private String mSurname;
    private Calendar mDateOfBirth;
    private String mPhoneNumber;
    private Gender mGender;

    public UserInfo() {
    }

    public UserInfo(String avatarLink, String userId, String name, String surname, Calendar dateOfBirth, String phoneNumber, Gender gender) {
        mAvatarLink = avatarLink;
        mUserId = userId;
        mName = name;
        mSurname = surname;
        mDateOfBirth = dateOfBirth;
        mPhoneNumber = phoneNumber;
        mGender = gender;
    }

    public String getAvatarLink() {
        return mAvatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        mAvatarLink = avatarLink;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

    public Calendar getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        mDateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return mGender;
    }

    public void setGender(Gender gender) {
        mGender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (mAvatarLink != null ? !mAvatarLink.equals(userInfo.mAvatarLink) : userInfo.mAvatarLink != null)
            return false;
        if (mUserId != null ? !mUserId.equals(userInfo.mUserId) : userInfo.mUserId != null) return false;
        if (mName != null ? !mName.equals(userInfo.mName) : userInfo.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(userInfo.mSurname) : userInfo.mSurname != null) return false;
        if (mDateOfBirth.getTime().getYear() != ((UserInfo) o).getDateOfBirth().getTime().getYear()) return false;
        if (mDateOfBirth.getTime().getMonth() != ((UserInfo) o).getDateOfBirth().getTime().getMonth()) return false;
        if (mDateOfBirth.getTime().getDay() != ((UserInfo) o).getDateOfBirth().getTime().getDay()) return false;
        if (mPhoneNumber != null ? !mPhoneNumber.equals(userInfo.mPhoneNumber) : userInfo.mPhoneNumber != null)
            return false;
        return mGender == userInfo.mGender;

    }

    @Override
    public int hashCode() {
        int result = mAvatarLink != null ? mAvatarLink.hashCode() : 0;
        result = 31 * result + (mUserId != null ? mUserId.hashCode() : 0);
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
        result = 31 * result + (mDateOfBirth != null ? mDateOfBirth.hashCode() : 0);
        result = 31 * result + (mPhoneNumber != null ? mPhoneNumber.hashCode() : 0);
        result = 31 * result + (mGender != null ? mGender.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "mAvatarLink='" + mAvatarLink + '\'' +
                ", mId='" + mUserId + '\'' +
                ", mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mDateOfBirth=" + mDateOfBirth +
                ", mPhoneNumber='" + mPhoneNumber + '\'' +
                ", mGender=" + mGender +
                '}';
    }
}
