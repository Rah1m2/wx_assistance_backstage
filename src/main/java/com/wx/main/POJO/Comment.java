package com.wx.main.POJO;

public class Comment {
    private int comment_id;
    private int article_id;
    private int user_id;
    private String user_name;
    private String user_avatarUrl;
    private String comment_content;
    private String comment_image;

    public Comment() {
    }

    public Comment(int comment_id, int article_id, int user_id, String user_name, String user_avatarUrl, String comment_content, String comment_image) {
        this.comment_id = comment_id;
        this.article_id = article_id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_avatarUrl = user_avatarUrl;
        this.comment_content = comment_content;
        this.comment_image = comment_image;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_image() {
        return comment_image;
    }

    public void setComment_image(String comment_image) {
        this.comment_image = comment_image;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", article_id=" + article_id +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_avatarUrl='" + user_avatarUrl + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_image='" + comment_image + '\'' +
                '}';
    }
}
