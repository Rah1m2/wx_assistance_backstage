package com.wx.main.POJO;

import javax.persistence.Transient;
import java.util.Date;

public class Student {
    private int user_mission_id;
    private int sort_id;
    private String user_openid;
    private String user_score;
    private String contact_detail;
    @Transient
    private String user_name;
    @Transient
    private String user_avatarUrl;
    @Transient
    private boolean isReserved;

    public Student() {
    }

    public Student(int user_mission_id, int sort_id, String user_openid, String user_score, String contact_detail, String user_name, String user_avatarUrl, boolean isReserved) {
        this.user_mission_id = user_mission_id;
        this.sort_id = sort_id;
        this.user_openid = user_openid;
        this.user_score = user_score;
        this.contact_detail = contact_detail;
        this.user_name = user_name;
        this.user_avatarUrl = user_avatarUrl;
        this.isReserved = isReserved;
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

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }


    @Override
    public String toString() {
        return "Student{" +
                "user_mission_id=" + user_mission_id +
                ", sort_id=" + sort_id +
                ", user_openid='" + user_openid + '\'' +
                ", user_score='" + user_score + '\'' +
                ", contact_detail='" + contact_detail + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_avatarUrl='" + user_avatarUrl + '\'' +
                ", isReserved=" + isReserved +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass()!=o.getClass())
            return false;
        Student student = (Student) o;
        return student.user_openid.equals(user_openid);
//                && student.sort_id == sort_id;
//                && student.user_mission_id == user_mission_id
//                && student.user_name.equals(user_name);
//                && student.user_avatarUrl.equals(user_avatarUrl)
//                && student.user_score.equals(user_score);
    }
}
