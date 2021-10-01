package com.wx.main.POJO;

import javax.persistence.Transient;

public class Reserve {
    private int user_mission_id;
    private int sort_id;
    private String user_openid;
    private String customer_user_openid;
    private Boolean isReserved = false;
    @Transient
    private String user_name;
    @Transient
    private String user_avatarUrl;
    @Transient
    private String user_score;

    public Reserve() {
    }

    public Reserve(int user_mission_id, int sort_id, String user_openid, String costomer_user_openid, Boolean isReserved, String user_name, String user_avatarUrl, String user_score) {
        this.user_mission_id = user_mission_id;
        this.sort_id = sort_id;
        this.user_openid = user_openid;
        this.customer_user_openid = costomer_user_openid;
        this.isReserved = isReserved;
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
                ", customer_user_openid='" + customer_user_openid + '\'' +
                ", isReserved=" + isReserved +
                ", user_name='" + user_name + '\'' +
                ", user_avatarUrl='" + user_avatarUrl + '\'' +
                ", user_score='" + user_score + '\'' +
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
                && reserve.sort_id == sort_id
                && reserve.user_mission_id == user_mission_id
                && reserve.user_name.equals(user_name)
                && reserve.user_avatarUrl.equals(user_avatarUrl)
                && reserve.user_score.equals(user_score);
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
