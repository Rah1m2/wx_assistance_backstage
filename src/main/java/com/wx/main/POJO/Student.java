package com.wx.main.POJO;

public class Student {
    private int user_mission_id;
    private int sort_id;
    private String user_openid;
    private String user_score;
    private String contact_detail;

    public Student() {
    }

    public Student(int user_mission_id, int sort_id, String user_openid, String user_score, String contact_detail) {
        this.user_mission_id = user_mission_id;
        this.sort_id = sort_id;
        this.user_openid = user_openid;
        this.user_score = user_score;
        this.contact_detail = contact_detail;
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

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    public String getContact_detail() {
        return contact_detail;
    }

    public void setContact_detail(String contact_detail) {
        this.contact_detail = contact_detail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "user_mission_id=" + user_mission_id +
                ", sort_id=" + sort_id +
                ", user_openid='" + user_openid + '\'' +
                ", user_score='" + user_score + '\'' +
                ", contact_detail='" + contact_detail + '\'' +
                '}';
    }
}
