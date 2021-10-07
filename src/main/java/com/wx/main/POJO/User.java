package com.wx.main.POJO;

import javax.persistence.Transient;

public class User {
    private String user_openid;
    private String user_name;
    private String user_gender;
    private String user_language;
    private String user_country;
    private String user_avatarUrl;
    @Transient
    private String user_identity;

    public User() {
    }

    public User(String user_openid, String user_name, String user_gender, String user_language, String user_country, String user_avatarUrl, String user_identity) {
        this.user_openid = user_openid;
        this.user_name = user_name;
        this.user_gender = user_gender;
        this.user_language = user_language;
        this.user_country = user_country;
        this.user_avatarUrl = user_avatarUrl;
        this.user_identity = user_identity;
    }

    public String getUser_openid() {
        return user_openid;
    }

    public void setUser_openid(String user_openid) {
        this.user_openid = user_openid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_language() {
        return user_language;
    }

    public void setUser_language(String user_language) {
        this.user_language = user_language;
    }

    public String getUser_country() {
        return user_country;
    }

    public void setUser_country(String user_country) {
        this.user_country = user_country;
    }

    public String getUser_avatarUrl() {
        return user_avatarUrl;
    }

    public void setUser_avatarUrl(String user_avatarUrl) {
        this.user_avatarUrl = user_avatarUrl;
    }

    public String getUser_identity() {
        return user_identity;
    }

    public void setUser_identity(String user_identity) {
        this.user_identity = user_identity;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_openid='" + user_openid + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_gender='" + user_gender + '\'' +
                ", user_language='" + user_language + '\'' +
                ", user_country='" + user_country + '\'' +
                ", user_avatarUrl='" + user_avatarUrl + '\'' +
                ", user_identity='" + user_identity + '\'' +
                '}';
    }
}
