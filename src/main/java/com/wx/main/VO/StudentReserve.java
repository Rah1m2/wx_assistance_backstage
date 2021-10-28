package com.wx.main.VO;

import javax.persistence.Transient;
import java.util.Date;

public class StudentReserve {
    private int user_mission_id;
    private int mission_id;
    private int sort_id;
    private String user_openid;
    private String user_name;
    private String user_avatarUrl;
    private String customer_user_name;
    private String customer_user_openid;
    private String customer_user_avatarUrl;
    private String user_score;
    private String contact_detail;
    private String sort_name;
    private boolean isReserved;
    private Date deadline;

    public StudentReserve() {
    }

    public StudentReserve(int user_mission_id, int mission_id, int sort_id, String user_openid, String user_name, String user_avatarUrl, String customer_user_name, String customer_user_openid, String customer_user_avatarUrl, String user_score, String contact_detail, String sort_name, boolean isReserved, Date deadline) {
        this.user_mission_id = user_mission_id;
        this.mission_id = mission_id;
        this.sort_id = sort_id;
        this.user_openid = user_openid;
        this.user_name = user_name;
        this.user_avatarUrl = user_avatarUrl;
        this.customer_user_name = customer_user_name;
        this.customer_user_openid = customer_user_openid;
        this.customer_user_avatarUrl = customer_user_avatarUrl;
        this.user_score = user_score;
        this.contact_detail = contact_detail;
        this.sort_name = sort_name;
        this.isReserved = isReserved;
        this.deadline = deadline;
    }

    public int getUser_mission_id() {
        return user_mission_id;
    }

    public void setUser_mission_id(int user_mission_id) {
        this.user_mission_id = user_mission_id;
    }

    public int getMission_id() {
        return mission_id;
    }

    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
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

    public String getCustomer_user_name() {
        return customer_user_name;
    }

    public void setCustomer_user_name(String customer_user_name) {
        this.customer_user_name = customer_user_name;
    }

    public String getCustomer_user_openid() {
        return customer_user_openid;
    }

    public void setCustomer_user_openid(String customer_user_openid) {
        this.customer_user_openid = customer_user_openid;
    }

    public String getCustomer_user_avatarUrl() {
        return customer_user_avatarUrl;
    }

    public void setCustomer_user_avatarUrl(String customer_user_avatarUrl) {
        this.customer_user_avatarUrl = customer_user_avatarUrl;
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

    public String getSort_name() {
        return sort_name;
    }

    public void setSort_name(String sort_name) {
        this.sort_name = sort_name;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "StudentReserve{" +
                "user_mission_id=" + user_mission_id +
                ", mission_id=" + mission_id +
                ", sort_id=" + sort_id +
                ", user_openid='" + user_openid + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_avatarUrl='" + user_avatarUrl + '\'' +
                ", customer_user_name='" + customer_user_name + '\'' +
                ", customer_user_openid='" + customer_user_openid + '\'' +
                ", customer_user_avatarUrl='" + customer_user_avatarUrl + '\'' +
                ", user_score='" + user_score + '\'' +
                ", contact_detail='" + contact_detail + '\'' +
                ", sort_name='" + sort_name + '\'' +
                ", isReserved=" + isReserved +
                ", deadline=" + deadline +
                '}';
    }
}
