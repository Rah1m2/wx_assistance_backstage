package com.wx.main.VO;

public class RedisCustomer {
    private int mission_id;
    private String sort_name;
    private String sort_id;
    private String user_openid;
    private String customer_user_openid;
    private String customer_user_name;
    private String customer_user_avatarUrl;
    private boolean isReserved;

    public RedisCustomer() {
    }

    public RedisCustomer(int mission_id, String sort_name, String sort_id, String user_openid, String customer_user_openid, String customer_user_name, String customer_user_avatarUrl, boolean isReserved) {
        this.mission_id = mission_id;
        this.sort_name = sort_name;
        this.sort_id = sort_id;
        this.user_openid = user_openid;
        this.customer_user_openid = customer_user_openid;
        this.customer_user_name = customer_user_name;
        this.customer_user_avatarUrl = customer_user_avatarUrl;
        this.isReserved = isReserved;
    }

    public int getMission_id() {
        return mission_id;
    }

    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }

    public String getSort_name() {
        return sort_name;
    }

    public void setSort_name(String sort_name) {
        this.sort_name = sort_name;
    }

    public String getSort_id() {
        return sort_id;
    }

    public void setSort_id(String sort_id) {
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

    public String getCustomer_user_name() {
        return customer_user_name;
    }

    public void setCustomer_user_name(String customer_user_name) {
        this.customer_user_name = customer_user_name;
    }

    public String getCustomer_user_avatarUrl() {
        return customer_user_avatarUrl;
    }

    public void setCustomer_user_avatarUrl(String customer_user_avatarUrl) {
        this.customer_user_avatarUrl = customer_user_avatarUrl;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public String toString() {
        return "RedisCustomer{" +
                "mission_id=" + mission_id +
                ", sort_name='" + sort_name + '\'' +
                ", sort_id='" + sort_id + '\'' +
                ", user_openid='" + user_openid + '\'' +
                ", customer_user_openid='" + customer_user_openid + '\'' +
                ", customer_user_name='" + customer_user_name + '\'' +
                ", customer_user_avatarUrl='" + customer_user_avatarUrl + '\'' +
                ", isReserved=" + isReserved +
                '}';
    }
}
