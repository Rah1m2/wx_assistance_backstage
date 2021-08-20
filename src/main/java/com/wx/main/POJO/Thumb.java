package com.wx.main.POJO;

public class Thumb {
    private int thumb_id;
    private int comment_id;
    private String user_openid;

    public Thumb() {
    }

    public Thumb(int thumb_id, int comment_id, String user_openid) {
        this.thumb_id = thumb_id;
        this.comment_id = comment_id;
        this.user_openid = user_openid;
    }

    public Thumb(int thumb_id, String user_openid) {
        this.thumb_id = thumb_id;
        this.user_openid = user_openid;
    }

    public int getThumb_id() {
        return thumb_id;
    }

    public void setThumb_id(int thumb_id) {
        this.thumb_id = thumb_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getUser_openid() {
        return user_openid;
    }

    public void setUser_openid(String user_openid) {
        this.user_openid = user_openid;
    }

    @Override
    public String toString() {
        return "Thumb{" +
                "thumb_id=" + thumb_id +
                ", comment_id=" + comment_id +
                ", user_openid='" + user_openid + '\'' +
                '}';
    }
}
