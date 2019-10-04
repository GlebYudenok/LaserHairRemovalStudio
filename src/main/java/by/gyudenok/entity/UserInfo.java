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
