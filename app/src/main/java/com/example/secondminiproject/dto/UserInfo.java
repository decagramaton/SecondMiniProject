package com.example.secondminiproject.dto;

public class UserInfo {
    private int userNo;
    private String userKoName;
    private String userId;
    private String userPassword;
    private long userBirth;
    private String userPhone;
    private String userGender;
    private String userEnName;
    private String userEmail;

    @Override
    public String toString() {
        return "UserInfo{" +
                "userNo=" + userNo +
                ", userKoName='" + userKoName + '\'' +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userBirth=" + userBirth +
                ", userPhone='" + userPhone + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userEnName='" + userEnName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserKoName() {
        return userKoName;
    }

    public void setUserKoName(String userKoName) {
        this.userKoName = userKoName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public long getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(long userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserEnName() {
        return userEnName;
    }

    public void setUserEnName(String userEnName) {
        this.userEnName = userEnName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
