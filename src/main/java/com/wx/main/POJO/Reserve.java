package com.wx.main.POJO;

import javax.persistence.Transient;

public class Reserve {
    private int user_mission_id;
    private int sort_id;
    private String user_openid;
    @Transient
    private String user_name;
    @Transient
    private String user_avatarUrl;
    @Transient
    private String user_score;

    public Reserve() {
    }

    public Reserve(int user_mission_id, int sort_id, String user_openid, String user_name, String user_avatarUrl, String user_score) {
        this.user_mission_id = user_mission_id;
        this.sort_id = sort_id;
        this.user_openid = user_openid;
        this.user_name = user_name;
        this.user_avatarUrl = user_avatarUrl;
        this.user_score = user_score;
    }

    public int getUser_mission_id() {
        return user_mission_id;
    }

    public void setUser_mission_id(int user_mission_id) {
        this.user_mission_id = user_mission_id;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
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

    public String getUser_avatarUrl() {
        return user_avatarUrl;
    }

    public void setUser_avatarUrl(String user_avatarUrl) {
        this.user_avatarUrl = user_avatarUrl;
    }

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "user_mission_id=" + user_mission_id +
                ", sort_id=" + sort_id +
                ", user_openid='" + user_openid + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_avatarUrl='" + user_avatarUrl + '\'' +
                ", user_score='" + user_score + '\'' +
                '}';
    }
}
