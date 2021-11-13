package com.wx.main.POJO;

import javax.persistence.Transient;
import java.util.Date;

public class Reserve {
    private int mission_id;
    private int sort_id;
    private String user_openid;
    private String customer_user_openid;
    private Boolean isReserved = false;
    private String deadline;

    public Reserve() {
    }

    public Reserve(int mission_id, int sort_id, String user_openid, String customer_user_openid, Boolean isReserved, String deadline) {
        this.mission_id = mission_id;
        this.sort_id = sort_id;
        this.user_openid = user_openid;
        this.customer_user_openid = customer_user_openid;
        this.isReserved = isReserved;
        this.deadline = deadline;
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

    public String getCustomer_user_openid() {
        return customer_user_openid;
    }

    public void setCustomer_user_openid(String customer_user_openid) {
        this.customer_user_openid = customer_user_openid;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "mission_id=" + mission_id +
                ", sort_id=" + sort_id +
                ", user_openid='" + user_openid + '\'' +
                ", customer_user_openid='" + customer_user_openid + '\'' +
                ", isReserved=" + isReserved +
                ", deadline='" + deadline + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass()!=o.getClass())
            return false;
        Reserve reserve = (Reserve) o;
        return reserve.user_openid.equals(user_openid)
                && reserve.sort_id == sort_id;
//                && reserve.user_mission_id == user_mission_id
//                && reserve.user_name.equals(user_name);
//                && reserve.user_avatarUrl.equals(user_avatarUrl)
//                && reserve.user_score.equals(user_score);
    }

//    @Override
//    public boolean equals(Object o){
//        if (this == o)
//            return true;
//        if (o == null || getClass()!=o.getClass())
//            return false;
//        Reserve reserve = (Reserve) o;
//        return reserve.user_openid.equals(user_openid)
//                && reserve.isReserved.equals(isReserved)
//                && reserve.customer_user_openid.equals(customer_user_openid)
//                && reserve.sort_id == sort_id
//                && reserve.user_avatarUrl.equals(user_avatarUrl)
//                && reserve.user_mission_id == user_mission_id
//                && reserve.user_name.equals(user_name)
//                && reserve.user_score.equals(user_score);
//    }
}
